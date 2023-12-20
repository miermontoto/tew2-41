package impl.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import com.tewrrss.business.resteasy.CommunityServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.UserComToken;
import com.tewrrss.dto.UserToken;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.CommunityServiceImpl;
import impl.tewrrss.business.UserServiceImpl;


public class CommunityServiceRsImpl extends CommunityServiceImpl implements CommunityServiceRs{

	private UserServiceImpl userImpl = new UserServiceImpl();
	
	/** Devuelve las comunidades a las que se unio un usuario dado 
	 * */
	
	@Override
	public List<Community> listJoined(UserToken user) {
		// Solo para el propio usuario y los administradores
		String emailUser= GestorSesion.getInstance().checkToken(user.getToken());
		if (emailUser.equals(user.getEmail()) || (userImpl.findByEmail(emailUser).get().getRole() == 1)) {
			return listJoined(ClassCreation.CreateUser(user));
		}
		return null;
		}

	@Override
	public String join(UserComToken UCK) {
		// Solo para el propio usuario y los administradores
		if (GestorSesion.getInstance().checkToken(UCK.getToken()) != null) {
			return join(UCK.getCommunity(),  UCK.getUser());
		}
		return null;
		}

	@Override
	public String leave(UserComToken UCK) {
		// Solo para el propio usuario y los administradores
		if (GestorSesion.getInstance().checkToken(UCK.getToken()) != null) {
			return leave(UCK.getCommunity(), UCK.getUser());
		}
		return null;
		}

	/** Crea una nueva comunidad 
	 * */ 
	@Override
	public String create(CommunityToken comunidad) {
		// Cualquier usuario registrado puede crearla
		if (GestorSesion.getInstance().checkToken(comunidad.getToken()) != null) {
			return create(ClassCreation.CreateCommunity(comunidad));
		}
		return null;
	}

	/** Elimina una comunidad 
	 * */
	@Override
	public String remove(CommunityToken comunidad) {
		// Solo puede borrarla un administrador
		if (userImpl.findByEmail(GestorSesion.getInstance().checkToken(comunidad.getToken())).get().getRole() == 1) {

			return remove(ClassCreation.CreateCommunity(comunidad));
		}
		return null;
	}

	/** Devuelve si el usuario puede unirse a la comunidad (Si no esta ya unido) 
	 * */
	@Override
	public boolean ableToJoin(UserComToken UCK) {
		// Solo para el propio usuario 
		if (GestorSesion.getInstance().checkToken(UCK.getToken()).equals(UCK.getUser().getEmail())) {
			return ableToJoin(UCK.getCommunity(), UCK.getUser());
		}
		return false;
	}

	/** Devuelve una comunidad con ese nombre si existe
	 * */
	@Override
	public Optional<Community> findByName(String name, String token) {
		// Cualquier usuario puede 
		if (GestorSesion.getInstance().checkToken(token) != null) {
			return findByName(name);
		}
		return null;
	}

	/**  Devuelve las counidades que contengan la cadena dada
	 * */
	@Override
	public List<Community> search(String search, String Token) {
		// Cualquier usuario puede buscar
		if (GestorSesion.getInstance().checkToken(Token) != null) {
			return search(search);
		}

		return null;
	}

}
