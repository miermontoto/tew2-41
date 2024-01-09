package impl.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import com.tewrrss.business.resteasy.CommunityServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.CommunityServiceImpl;

public class CommunityServiceRsImpl extends CommunityServiceImpl implements CommunityServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public String create(String token, Community com) {
		User user = gestor.getUser(token);
		if (user == null) return "error";

		return create(com);
	}

	@Override
	public String remove(String token, Community com) {
		User user = gestor.getUser(token);
		if (user.getRole() != Role.ADMIN) return "error"; 

		return remove(com);
	}

	@Override
	public Community findByName(String name, String token) {
		if (gestor.getUser(token) == null) return null;

		Optional<Community> com = findByName(name);
		return com.isPresent() ? com.get() : null;
	}

	@Override
	public List<Community> search(String search, String Token) {
		if (gestor.getUser(Token) == null) return null;
		return search(search);
	}
}
