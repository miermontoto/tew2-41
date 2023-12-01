package impl.tewrrss.business.users;

import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class Update {

	public String update(User user) {
		return Factories.persistence.getUserDAO().update(user) ? "success" : "error";
	}

}
