package impl.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotAuthorizedException;

import com.tewrrss.business.resteasy.UserServiceRs;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserToken;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.UserServiceImpl;

public class UserServiceRsImpl extends UserServiceImpl implements UserServiceRs {

	
	/**  Devuelve una lista con todos los usuarios
	 * */
	@Override
	public List<User> listAll(String token) {
		// Solo un administrador deberia hacer esto (Actualmente admin es rol 0)
		if (findByEmail(GestorSesion.getInstance().checkToken(token)).get().getRole() == 0) {
			return listAll();
		}		
		return null;
	}

	/** Devuelve el usuario con el email dado 
	 * */
	@Override
	public Optional<User> findByEmail(String email, String token)
			throws EntityNotFoundException, NotAuthorizedException {
		// Solo para administradores
		if (findByEmail(GestorSesion.getInstance().checkToken(token)).get().getRole() == 0) {
			return findByEmail(email);
		}
		return null;
	}

	/** Borra el usuario dado 
	 * */
	@Override
	public String remove(UserToken user) {
		// Solo para el propio usuario o un administrador
		String emailUser= GestorSesion.getInstance().checkToken(user.getToken());
		if (emailUser.equals(user.getEmail()) || (findByEmail(emailUser).get().getRole() == 1)) {
			return remove(ClassCreation.CreateUser(user));
		}
		return null;
	}

	/** Añade el usuario dado
	 * */
	@Override
	public String add(User user) {
		// Usado en el registro, aun no hay token
		return add(user);
	}

	
	@Override
	public String update(UserToken user) throws EntityNotFoundException {
		// Solo el usuario puede modificar su cuenta
		if (GestorSesion.getInstance().checkToken(user.getToken()).equals(user.getEmail())) {
			return update(ClassCreation.CreateUser(user));
		}
		return null;
	}

	/** Devuelve los usuarios de la comunidad dada
	 * */
	@Override
	public List<User> getUsersInCommunity(CommunityToken community) {
		if (GestorSesion.getInstance().checkToken(community.getToken()) != null) {
			return getUsersInCommunity(ClassCreation.CreateCommunity(community));
		}
		return null;
	}




}
