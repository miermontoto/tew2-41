package impl.tewrrss.business.members;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class ListJoined {

	public List<Community> listJoined(User user) {
		return Factories.persistence.getMemberDAO().getJoinedCommunities(user);
	}
}
