package impl.tewrrss.business.communities;

import java.util.Optional;

import com.tewrrss.dto.Community;
import com.tewrrss.infrastructure.Factories;

public class FindByName {

	public Optional<Community> findByName(String name) {
		return Factories.persistence.getCommunityDAO().findByName(name);
	}

}
