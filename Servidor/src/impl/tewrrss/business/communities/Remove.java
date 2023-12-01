package impl.tewrrss.business.communities;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.util.Role;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class Remove {

	public String remove(Community community) {
		return Factories.persistence.getCommunityDAO().remove(community) ? "success" : "error";
	}
}
