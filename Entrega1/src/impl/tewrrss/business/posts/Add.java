package impl.tewrrss.business.posts;

import com.tewrrss.dto.Post;
import com.tewrrss.infrastructure.Factories;

public class Add {
	public String add(Post post) {
		return Factories.persistence.getPostDAO().add(post) ? "success" : "error";
	}
}
