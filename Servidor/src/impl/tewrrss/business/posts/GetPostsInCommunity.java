package impl.tewrrss.business.posts;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.infrastructure.Factories;

public class GetPostsInCommunity {

	public List<Post> getPostsFromCommunity(Community community) {
		return Factories.persistence.getPostDAO().getPostsInCommunity(community);
	}
}
