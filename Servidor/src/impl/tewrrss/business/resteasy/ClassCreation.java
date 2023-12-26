package impl.tewrrss.business.resteasy;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.PostToken;
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserToken;

public class ClassCreation {

	private ClassCreation() {}

	public static Post createPost(PostToken post) {
		return new Post(post.getContent(), post.getCreationDate(), post.getUserEmail(), post.getCommunityName(), post.getUserName());
	}

	public static User createUser(UserToken user) {
		return new User(user.getEmail(), user.getUsername(), user.getPassword(), user.getRole());
	}

	public static Community createCommunity(CommunityToken com) {
		return new Community(com.getName(), com.getDescription());
	}

}
