package impl.tewrrss.business.communities;

import com.tewrrss.dto.Community;
import com.tewrrss.infrastructure.Factories;

import impl.tewrrss.persistence.jdbc.CommunityJdbcDAO;

public class Create {

	public String create(Community comunidad) {
		return Factories.persistence.getCommunityDAO().add(comunidad) ? "success" : "error";
	}

}
