package impl.tewrrss.business.resteasy;

import com.tewrrss.business.resteasy.LoginServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.infrastructure.GestorSesion;

public class LoginServiceRsImpl implements LoginServiceRs{

	@Override
	public String login(User user) {
		System.out.println("La contraseña es " + user.getPassword());
		//if (Factories.services.createLoginService().verify(user.getEmail(), user.getPassword()) != null)
			//Si el usuario existe
			//System.out.print("Ha pasado el IF. El token es " + GestorSesion.getInstance().registrarLogin(user.getEmail()));
		return GestorSesion.getInstance().registrarLogin(user.getEmail()); // Retorna un token, para devolverlo
		//return "";
	}
	
	@Override
	public String logon(String token) {

		if (GestorSesion.getInstance().checkToken(token) != null) 
			//Si el usuario existe
			return GestorSesion.getInstance().closeLogin(token); // Retorna un token, para devolverlo
		return "error";
	}
		
	
}
