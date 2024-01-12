package impl.tewrrss.business.resteasy;

import java.util.List;

import com.tewrrss.business.resteasy.MemberServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.dto.resteasy.CommunityRequestData;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.MemberServiceImpl;

public class MemberServiceRsImpl extends MemberServiceImpl implements MemberServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public List<Community> listJoined(String token) {
		User user = gestor.getUser(token);
		if (user == null) return null;

		return listJoined(user);
	}

	@Override
	public String join(CommunityRequestData data) {
		User user = gestor.getUser(data.getToken());
		if (user == null) return null;

		return join(data, user);
	}

	@Override
	public String leave(CommunityRequestData data) {
		User user = gestor.getUser(data.getToken());
		if (user == null) return null;

		// TODO: comprobar si está unido a la comunidad o no.

		return leave(data, user);
	}

}
