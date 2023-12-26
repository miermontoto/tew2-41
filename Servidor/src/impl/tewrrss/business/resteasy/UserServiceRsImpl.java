package impl.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotAuthorizedException;

import com.tewrrss.business.UserService;
import com.tewrrss.business.resteasy.UserServiceRs;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserToken;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.UserServiceImpl;

public class UserServiceRsImpl extends UserServiceImpl implements UserServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public List<User> listAll(String token) {
		// Solo un administrador deberia hacer esto (Actualmente admin es rol 0)
		String checkToken = gestor.checkToken(token);
		if (checkToken == null) return null;

		Optional<User> user = findByEmail(checkToken);
		if (!user.isPresent() || user.get().getRole() != Role.ADMIN) return null;

		return listAll();
	}

	@Override
	public User findByEmail(String email, String token) throws EntityNotFoundException, NotAuthorizedException {
		// Solo para administradores
		Optional<User> users = findByEmail(gestor.checkToken(token));
		if (!users.isPresent() || users.get().getRole() != Role.ADMIN) return null;

		return findByEmail(email).isPresent() ? findByEmail(email).get() : null;
	}

	@Override
	public String remove(UserToken user) {
		// Solo para el propio usuario o un administrador
		String emailReal = gestor.checkToken(user.getToken());
		if (emailReal.equals(user.getEmail()) || (findByEmail(emailReal).get().getRole() == 1)) {
			return remove(ClassCreation.createUser(user));
		}
		return null;
	}

	@Override
	public String add(User user) {
		// Usado en el registro, aun no hay token
		UserService service = new UserServiceImpl();
		String success = service.add(user);

		return success.equals("success") ?  gestor.registrarLogin(user.getEmail()) : success;
	}


	@Override
	public String update(UserToken user) throws EntityNotFoundException {
		// Solo el usuario puede modificar su cuenta
		if (!gestor.checkToken(user.getToken()).equals(user.getEmail())) return null;
		return update(ClassCreation.createUser(user));
	}

	@Override
	public List<User> getUsersInCommunity(CommunityToken community) {
		if (gestor.checkToken(community.getToken()) == null) return null;
		return getUsersInCommunity(ClassCreation.createCommunity(community));
	}

}
