package impl.tewrrss.business.resteasy;

import java.util.List;

import com.tewrrss.business.resteasy.PostServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.PostServiceImpl;

public class PostServiceRsImpl extends PostServiceImpl implements PostServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public String add(String token, Post post) {
		User user = gestor.getUser(token);
		if (user == null) return null;
		if (!post.getUserEmail().equals(user.getEmail())) return null;
		
		return add(post);
	}

	@Override
	public String remove(String token, Post post) {
		User user = gestor.getUser(token);
		if (user == null) return null;
		if (!post.getUserEmail().equals(user.getEmail()) && user.getRole() != Role.ADMIN) return null;
		
		return remove(post);
	}

	@Override
	public List<Post> getPostsByUser(String token, User targetUser) {
		User loggedUser = gestor.getUser(token);
		if (loggedUser == null) return null;

		// comprobar la igualdad de emails es una manera fiable de comparar usuarios
		if (!loggedUser.getEmail().equals(targetUser.getEmail()) && loggedUser.getRole() != Role.ADMIN) return null;
		
		return getPostsByUser(targetUser);
	}

	@Override
	public List<Post> getPostsInCommunity(Community com) {
		return getPostsInCommunity(com);
	}

	@Override
	public List<Post> getNewPosts(String token) {
		User user = gestor.getUser(token);
		return getNewPosts(user);
	}

	@Override
	public List<Post> getPostsByUserInCommunity(User user, Community com) {
		return getPostsByUserInCommunity(user, com);
	}

}
