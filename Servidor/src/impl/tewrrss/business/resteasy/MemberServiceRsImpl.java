package impl.tewrrss.business.resteasy;

import java.util.List;

import com.tewrrss.business.resteasy.MemberServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserComToken;
import com.tewrrss.dto.UserToken;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.MemberServiceImpl;
import impl.tewrrss.business.UserServiceImpl;

public class MemberServiceRsImpl extends MemberServiceImpl implements MemberServiceRs {

	private UserServiceImpl userImpl = new UserServiceImpl();
	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public List<Community> listJoined(UserToken user) {
		String emailUser = gestor.checkToken(user.getToken());
		if (emailUser == null) return null;
		if (!emailUser.equals(user.getEmail()) && userImpl.findByEmail(emailUser).get().getRole() != Role.ADMIN) return null;

		return listJoined(ClassCreation.createUser(user));
	}

	@Override
	public String join(UserComToken UCK) {
		if (gestor.checkToken(UCK.getToken()) == null) return null;
		return join(UCK.getCommunity(), UCK.getUser());
	}

	@Override
	public String leave(UserComToken UCK) {
		if (gestor.checkToken(UCK.getToken()) == null) return null;
		return leave(UCK.getCommunity(), UCK.getUser());
	}

	@Override
	public boolean ableToJoin(UserComToken UCK) {
		if (gestor.checkToken(UCK.getToken()) == null) return false;

		return ableToJoin(UCK.getCommunity(), UCK.getUser());
	}

	@Override
	public List<User> getUsersInCommunity(CommunityToken community) {
		if (gestor.checkToken(community.getToken()) == null) return null;
		return getUsersInCommunity(ClassCreation.createCommunity(community));
	}

}
