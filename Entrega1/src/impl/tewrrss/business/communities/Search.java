package impl.tewrrss.business.communities;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.infrastructure.Factories;

public class Search {

	public List<Community> search(String search) {
		return Factories.persistence.getCommunityDAO().search(search);
	}

}
