package impl.tewrrss.business.members;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class Join {

	public String join(Community community, User user) {
		return Factories.persistence.getMemberDAO().join(community, user) ? "success" : "error";
	}

}
