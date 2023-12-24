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

	GestorSesion gestor= GestorSesion.getInstance();

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
	public User findByEmail(String email, String token)
			throws EntityNotFoundException, NotAuthorizedException {
		// Solo para administradores
		Optional<User> users= findByEmail(GestorSesion.getInstance().checkToken(token));
		if (users.isPresent() &&  users.get().getRole() == 0) {	// Comprobamos que exista usuario y sea administrador
			return findByEmail(email).isPresent() ? findByEmail(email).get() : null;
		}
		return null;
	}

	/** Borra el usuario dado 
	 * */
	@Override
	public String remove(UserToken user) {
		// Solo para el propio usuario o un administrador
		String emailReal= GestorSesion.getInstance().checkToken(user.getToken());
		if (emailReal.equals(user.getEmail()) || (findByEmail(emailReal).get().getRole() == 1)) {
			return remove(ClassCreation.CreateUser(user));
		}
		return null;
	}

	/** Añade el usuario dado y devuelve el token
	 * */
	@Override
	public String add(User user) {
		// Usado en el registro, aun no hay token

		com.tewrrss.business.UserService service = new impl.tewrrss.business.UserServiceImpl();
		String succes= service.add(user);

		return succes.equals("success") ?  gestor.registrarLogin(user.getEmail()) : succes;
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
