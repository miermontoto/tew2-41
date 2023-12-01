package impl.tewrrss.business.posts;

import java.util.List;

import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.PersistenceFactory;

public class GetNewPosts {
	public List<Post> getNewPosts(User user){
		return Factories.persistence.getPostDAO().getPostsInCommunitiesOfUser(user);
	}
}
