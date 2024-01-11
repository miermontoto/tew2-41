package impl.tewrrss.business.resteasy;

import com.tewrrss.business.resteasy.LoginServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.infrastructure.GestorSesion;

public class LoginServiceRsImpl implements LoginServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	// Metodo para el login de usuarios.
	@Override
	public String login(User user) {
		User u = Factories.services.createLoginService().verify(user.getEmail(), user.getPassword());
		if (u == null) return "nullUser";
		return gestor.register(u);
	}

	@Override
	public String logout(String token) {
		if (gestor.getUser(token) != null) return "alreadyLoggedOut";
		return gestor.expire(token);
	}

	@Override
	public User myUser(String token) {
		return gestor.getUser(token);
	}

}
