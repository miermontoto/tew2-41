package impl.tewrrss.business.communities;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class Join {

	public String join(Community community, User user) {
		return Factories.persistence.getCommunityDAO().join(community, user) ? "success" : "error";
	}
	
}
