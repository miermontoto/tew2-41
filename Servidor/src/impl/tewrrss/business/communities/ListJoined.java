package impl.tewrrss.business.communities;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class ListJoined {

	public List<Community> listJoined(User user){
		return Factories.persistence.getCommunityDAO().getJoinedCommunities(user);
	}
}
