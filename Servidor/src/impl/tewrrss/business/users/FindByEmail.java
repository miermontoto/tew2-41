package impl.tewrrss.business.users;

import java.util.Optional;

import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class FindByEmail {

	public Optional<User> findByEmail(String email) {
		return Factories.persistence.getUserDAO().findByEmail(email);
	}

}
