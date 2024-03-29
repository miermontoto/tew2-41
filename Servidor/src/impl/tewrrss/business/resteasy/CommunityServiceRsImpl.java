package impl.tewrrss.business.resteasy;

import com.tewrrss.business.resteasy.CommunityServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.dto.resteasy.CommunityRequestData;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.CommunityServiceImpl;

public class CommunityServiceRsImpl extends CommunityServiceImpl implements CommunityServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public String create(CommunityRequestData data) {
		User user = gestor.getUser(data.getToken());
		if (user == null) return "invalidToken";
		if (data.getName().contains(" ")) return "hasSpaces";

		return super.create(data);
	}

	@Override
	public String remove(CommunityRequestData data) {
		User user = gestor.getUser(data.getToken());
		if (user == null) return "invalidToken";
		if (user.getRole() != Role.ADMIN) return "userNotAdmin";

		return super.remove(data);
	}

}
