package impl.tewrrss.business.communities;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public class AbleToJoin {

	public boolean ableToJoin(Community comunidad, User user) {
		return new ListJoined().listJoined(user).stream().noneMatch(c -> c.getName().equals(comunidad.getName()));
	}

}
