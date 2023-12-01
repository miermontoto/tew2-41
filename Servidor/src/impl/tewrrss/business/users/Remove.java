package impl.tewrrss.business.users;

import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class Remove {

	public String remove(User user) {
		return Factories.persistence.getUserDAO().remove(user) ? "success" : "error";
	}

}
