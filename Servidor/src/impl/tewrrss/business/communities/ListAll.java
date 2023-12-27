package impl.tewrrss.business.communities;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.infrastructure.Factories;

public class ListAll {

	public List<Community> listAll() {
		return Factories.persistence.getCommunityDAO().getCommunities();
	}

}
