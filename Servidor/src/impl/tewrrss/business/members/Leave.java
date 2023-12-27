package impl.tewrrss.business.members;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class Leave {

	public String leave(Community community, User user) {
		return Factories.persistence.getMemberDAO().leave(community, user) ? "success" : "error";
	}
}
