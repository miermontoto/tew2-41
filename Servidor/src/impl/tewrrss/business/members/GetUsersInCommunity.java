package impl.tewrrss.business.members;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class GetUsersInCommunity {

	public List<User> getUsersInCommunity(Community community) {
		return Factories.persistence.getMemberDAO().getUsersInCommunity(community);
	}

}
