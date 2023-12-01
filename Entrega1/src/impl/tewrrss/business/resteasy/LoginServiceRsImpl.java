package impl.tewrrss.business.resteasy;

import com.tewrrss.business.resteasy.LoginServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.infrastructure.GestorSesion;

public class LoginServiceRsImpl implements LoginServiceRs{

	@Override
	public String login(User user) {

		if (Factories.services.createLoginService().verify(user.getEmail(), user.getPassword()) != null)
			//Si el usuario existe
			return GestorSesion.getInstance().registrarLogin(user.getEmail());

		return "";
	} 
}
