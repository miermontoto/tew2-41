package impl.tewrrss.business.communities;

import com.tewrrss.dto.Community;
import com.tewrrss.infrastructure.Factories;

public class Remove {

	public String remove(Community community) {
		return Factories.persistence.getCommunityDAO().remove(community) ? "success" : "error";
	}
}
