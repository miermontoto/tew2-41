package impl.tewrrss.business;


import java.util.List;
import java.util.Optional;

import com.tewrrss.business.CommunityService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.business.communities.*;

public class CommunityServiceImpl implements CommunityService {

	@Override
	public String create(Community comunidad) {
		return new Create().create(comunidad);
	}

	@Override
	public String remove(Community comunidad) {
		return new Remove().remove(comunidad);
	}

	@Override
	public List<Community> listAll() {
		return new ListAll().listAll();
	}

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
	public boolean ableToJoin(Community comunidad, User user) {
		return new AbleToJoin().ableToJoin(comunidad, user);
	}

	@Override
	public Optional<Community> findByName(String name) {
		return new FindByName().findByName(name);
	}

	@Override
	public List<Community> search(String search) {
		return new Search().search(search);
	}

}
