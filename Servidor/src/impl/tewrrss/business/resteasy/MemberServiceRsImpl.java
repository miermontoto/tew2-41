package impl.tewrrss.business.resteasy;

import java.util.List;

import com.tewrrss.business.resteasy.MemberServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.dto.resteasy.Token;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.MemberServiceImpl;

public class MemberServiceRsImpl extends MemberServiceImpl implements MemberServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public List<Community> listJoined(String token) {
		System.out.println(token);
		User user = gestor.getUser(token);
		if (user == null) return null;

		return listJoined(user);
	}

	@Override
	public String join(String token, Community com) {
		User user = gestor.getUser(token);
		if (user == null) return null;

		return join(com, user);
	}

	@Override
	public String leave(String token, Community com) {
		User user = gestor.getUser(token);
		if (user == null) return null;

		// TODO: comprobar si está unido a la comunidad o no.

		return leave(com, user);
	}

	@Override
	public boolean ableToJoin(String token, Community com) {
		User user = gestor.getUser(token);
		if (user == null) return false;

		return ableToJoin(com, user);
	}

}
