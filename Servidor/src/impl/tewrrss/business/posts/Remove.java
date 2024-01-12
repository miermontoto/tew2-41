package impl.tewrrss.business.posts;

import com.tewrrss.dto.Post;
import com.tewrrss.infrastructure.Factories;

public class Remove {
	public String remove(Post post) {
		return Factories.persistence.getPostDAO().remove(post) ? "success" : "error";
	}
}
