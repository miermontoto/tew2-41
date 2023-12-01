package impl.tewrrss.business.posts;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;

public class GetPostsByUserInCommunity {
	public List<Post> getPostsByUserInCommunity(User user, Community community) {
		return Factories.persistence.getPostDAO().getPostsByUserInCommunity(user, community);
	}
}
