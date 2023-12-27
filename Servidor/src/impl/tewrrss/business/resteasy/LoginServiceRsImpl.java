package impl.tewrrss.business.resteasy;

import com.tewrrss.business.resteasy.LoginServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.GestorSesion;

public class LoginServiceRsImpl implements LoginServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public String login(User user) {
		return gestor.registrarLogin(user.getEmail()); // Retorna un token, para devolverlo
	}

	@Override
	public String logout(String token) {
		if (gestor.checkToken(token) != null) return "error";
		return gestor.closeLogin(token);
	}

}
