package impl.tewrrss.business.resteasy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tewrrss.business.resteasy.LoginServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.infrastructure.GestorSesion;

public class LoginServiceRsImpl implements LoginServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();
	private Map<String, List<LocalDateTime>> invalidLogins = gestor.getInvalidLogins();

	@Override
	public String login(User user) {
		// comprobar si el usuario está bloqueado (primero, si hay intentos fallidos)
		if (invalidLogins.containsKey(user.getEmail())) {
			LocalDateTime now = LocalDateTime.now();

			// obtener número de intentos fallidos en los últimos 5 minutos
			List<LocalDateTime> invalids = invalidLogins.get(user.getEmail());
			if (invalids.stream().filter(date -> date.isAfter(now.minusMinutes(5))).collect(Collectors.toList()).size() >= 3) {
				return "blocked";
			}

			// limpiar intentos fallidos para aliviar carga
			invalids.removeIf(date -> date.isBefore(now.minusMinutes(5)));
			if (invalids.isEmpty()) invalidLogins.remove(user.getEmail());
			else invalidLogins.put(user.getEmail(), invalids);
		}

		User u = Factories.services.createLoginService().verify(user.getEmail(), user.getPassword());
		if (u == null) return "nullUser";
		if (u.getEmail().equals("invalidAuth")) {
			if (invalidLogins.get(user.getEmail()) == null) {
				invalidLogins.put(user.getEmail(), new ArrayList<>());
			}

			invalidLogins.get(user.getEmail()).add(LocalDateTime.now());
			return "invalidAuth";
		}
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

	@Override
	public User getUserByMail(String email) {
		Optional<User> user = Factories.services.createUserService().findByEmail(email);
		if (user.isPresent()) return user.get();
		return null;
	}

}
