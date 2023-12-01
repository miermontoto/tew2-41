package impl.tewrrss.business.users;

import java.util.List;

import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class ListAll {

	public List<User> listAll() {
		return Factories.persistence.getUserDAO().listAll();
	}

}
