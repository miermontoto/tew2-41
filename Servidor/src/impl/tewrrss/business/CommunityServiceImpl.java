package impl.tewrrss.business;

import java.util.List;
import java.util.Optional;

import com.tewrrss.business.CommunityService;
import com.tewrrss.dto.Community;

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
	public Optional<Community> findByName(String name) {
		return new FindByName().findByName(name);
	}

	@Override
	public List<Community> search(String search) {
		return new Search().search(search);
	}

}
