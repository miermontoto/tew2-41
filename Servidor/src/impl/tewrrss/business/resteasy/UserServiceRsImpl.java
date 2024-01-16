package impl.tewrrss.business.resteasy;

import java.util.List;

import com.tewrrss.business.UserService;
import com.tewrrss.business.resteasy.UserServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.dto.resteasy.UserRequestData;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.UserServiceImpl;

public class UserServiceRsImpl extends UserServiceImpl implements UserServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public List<User> listAll(String token) {
		User user = gestor.getUser(token);
		if (user == null || user.getRole() != Role.ADMIN) return null;

		return listAll();
	}

	@Override
	public String add(User user) {
		UserService service = new UserServiceImpl();
		String result = service.add(user);

		return result.equals("success") ? gestor.register(user) : result;
	}

	@Override
	public String batchAdd(UserRequestData user) {
		User admin = gestor.getUser(user.getToken());
		if (admin == null || admin.getRole() != Role.ADMIN) return "unauthorized";

		return this.add(user);
	}

	@Override
	public String update(UserRequestData user) {
		User userReal = gestor.getUser(user.getToken());
		
		if (!userReal.getEmail().equals(user.getEmail()) && userReal.getRole() != Role.ADMIN) return "unauthorized";
		if (user.getUsername().equals("") || user.getUsername() == null) return "emptyUser";
		User dbUser = Factories.services.createLoginService().verify(user.getEmail(), user.getPassword());
		if (dbUser.getEmail().equals("invalidAuth")) return "invalidOldPasswd";
		
		// En caso de contraseña nueva vacia no se cambia la contraseña
		if (user.getNewPassword().equals("") || user.getNewPassword() == null) {
			return super.update(new User(user.getEmail(), user.getUsername(), dbUser.getPassword(), user.getRole()));
		}
		
		if (user.getNewPassword().equals(dbUser.getPassword())) return "samePasswords";
		
		// en este caso se modifican tanto el usuario como la contraseña
		return super.update(new User(user.getEmail(), user.getUsername(), user.getNewPassword(), user.getRole()));
	}

}
