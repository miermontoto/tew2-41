package impl.tewrrss.business.posts;

import java.util.List;

import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.Factories;
import com.tewrrss.persistence.PersistenceFactory;

import impl.tewrrss.persistence.jdbc.PostJdbcDAO;

public class GetPostsByUser {
	public List<Post> getPostsByUser(User user) {
		return Factories.persistence.getPostDAO().getPostsByUser(user);
	}
}
