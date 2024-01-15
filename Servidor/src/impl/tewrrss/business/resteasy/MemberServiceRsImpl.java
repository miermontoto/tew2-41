package impl.tewrrss.business.resteasy;

import java.util.List;

import com.tewrrss.business.resteasy.MemberServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.dto.resteasy.CommunityRequestData;
import com.tewrrss.dto.resteasy.UserRequestData;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.MemberServiceImpl;

public class MemberServiceRsImpl extends MemberServiceImpl implements MemberServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public List<Community> listMyJoined(String token) {
		User user = gestor.getUser(token);
		if (user == null) return null;

		return super.listJoined(user);
	}

	@Override
	public String join(CommunityRequestData data) {
		User user = gestor.getUser(data.getToken());
		if (user == null) return null;

		return super.join(data, user);
	}

	@Override
	public String leave(CommunityRequestData data) {
		User user = gestor.getUser(data.getToken());
		if (user == null) return null;

		return super.leave(data, user);
	}

	@Override
	public List<Community> listJoined(UserRequestData data) {
		return super.listJoined(data);
	}

}
