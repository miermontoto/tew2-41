package impl.tewrrss.business.posts;

import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.util.Role;

public class AbleToRemove {

	public boolean ableToRemove(Post post, User user) {
		return post.getUserEmail().equals(user.getEmail()) || user.getRole() == Role.ADMIN;
	}
}
