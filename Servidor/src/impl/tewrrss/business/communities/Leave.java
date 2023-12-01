package impl.tewrrss.business.communities;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class Leave {

	public String leave(Community community, User user) {
		return Factories.persistence.getCommunityDAO().leave(community, user) ? "success" : "error";
	}
}
