package impl.tewrrss.business.resteasy;

import com.tewrrss.business.resteasy.LoginServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.infrastructure.GestorSesion;

import javafx.util.Pair;

public class LoginServiceRsImpl implements LoginServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	// Metodo para el login de usuarios.
	@Override
	public Object[] login(User user) {
		User u = Factories.services.createLoginService().verify(user.getEmail(), user.getPassword());
		if (u == null) return null;
		return new Object[] { u, gestor.register(u) };
	}

	@Override
	public String logout(String token) {
		if (gestor.getUser(token) != null) return "error";
		return gestor.expire(token);
	}

}
