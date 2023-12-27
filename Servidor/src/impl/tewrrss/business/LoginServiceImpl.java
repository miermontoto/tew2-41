package impl.tewrrss.business;

import java.util.Optional;

import com.tewrrss.business.LoginService;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class LoginServiceImpl implements LoginService {

	@Override
	public User verify(String email, String password) {
		Optional<User> user = Factories.persistence.getUserDAO().findByEmail(email);
		if (!user.isPresent() || !user.get().getPassword().equals(password)) return null;
		return user.get();
	}

	@Override
	public boolean emailExists(String email) {
		return Factories.persistence.getUserDAO().findByEmail(email).isPresent();
	}

	@Override
	public void register(User user) {
		Factories.persistence.getUserDAO().add(user);
	}
}
