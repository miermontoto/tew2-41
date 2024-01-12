package impl.tewrrss.business;

import java.util.List;

import com.tewrrss.business.MemberService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.business.members.AbleToJoin;
import impl.tewrrss.business.members.GetUsersInCommunity;
import impl.tewrrss.business.members.Join;
import impl.tewrrss.business.members.Leave;
import impl.tewrrss.business.members.ListJoined;

public class MemberServiceImpl implements MemberService {

	@Override
	public List<Community> listJoined(User user) {
		return new ListJoined().listJoined(user);
	}

	@Override
	public String join(Community community, User user) {
		if (!ableToJoin(community, user)) return "unable";
		return new Join().join(community, user);
	}

	@Override
	public String leave(Community community, User user) {
		return new Leave().leave(community, user);
	}

	@Override
	public List<User> getUsersInCommunity(Community community) {
		return new GetUsersInCommunity().getUsersInCommunity(community);
	}

	@Override
	public boolean ableToJoin(Community comunidad, User user) {
		return new AbleToJoin().ableToJoin(comunidad, user);
	}

}
