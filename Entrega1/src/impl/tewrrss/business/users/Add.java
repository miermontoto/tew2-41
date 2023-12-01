package impl.tewrrss.business.users;

import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class Add {

	public String add(User user) {
		return Factories.persistence.getUserDAO().add(user) ? "success" : "error";
	}

}
