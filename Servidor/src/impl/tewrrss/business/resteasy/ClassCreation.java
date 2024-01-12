package impl.tewrrss.business.resteasy;

import com.tewrrss.dto.*;
import com.tewrrss.dto.resteasy.*;

public class ClassCreation {

	private ClassCreation() {}

	public static Post createPost(PostRequestData post) {
		return new Post(post.getContent(), post.getCreationDate(), post.getUserEmail(), post.getCommunityName(), post.getUserName());
	}

	public static User createUser(UserRequestData user) {
		return new User(user.getEmail(), user.getUsername(), user.getPassword(), user.getRole());
	}

	public static Community createCommunity(CommunityRequestData com) {
		return new Community(com.getName(), com.getDescription());
	}

}
