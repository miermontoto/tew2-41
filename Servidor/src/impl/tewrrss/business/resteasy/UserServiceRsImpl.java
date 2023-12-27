package impl.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotAuthorizedException;

import com.tewrrss.business.UserService;
import com.tewrrss.business.resteasy.UserServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserToken;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

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
		String checkToken = gestor.checkToken(token);
		if (checkToken == null) return null;

		Optional<User> currentToken = findByEmail(checkToken);
		if (!currentToken.isPresent() || currentToken.get().getRole() != Role.ADMIN) return null;

		Optional<User> user = findByEmail(email);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public String remove(UserToken user) {
		// Solo para el propio usuario o un administrador
		String emailReal = gestor.checkToken(user.getToken());
		if (emailReal == null) return null;

		if (!emailReal.equals(user.getEmail()) && findByEmail(emailReal).get().getRole() != Role.ADMIN) {
			return null;
		}

		return remove(ClassCreation.createUser(user));
	}

	@Override
	public String add(User user) {
		// Usado en el registro, aun no hay token
		UserService service = new UserServiceImpl();
		String success = service.add(user);

		return success.equals("success") ? gestor.registrarLogin(user.getEmail()) : success;
	}

	@Override
	public String update(UserToken user) throws EntityNotFoundException {
		// Solo el usuario puede modificar su cuenta
		if (!gestor.checkToken(user.getToken()).equals(user.getEmail())) return null;
		return update(ClassCreation.createUser(user));
	}

}
