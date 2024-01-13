package impl.tewrrss.business.resteasy;

import java.time.LocalDateTime;
import java.util.List;

import com.tewrrss.business.resteasy.PostServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;
import com.tewrrss.dto.resteasy.*;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.PostServiceImpl;

public class PostServiceRsImpl extends PostServiceImpl implements PostServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public String add(PostRequestData data) {
		User user = gestor.getUser(data.getToken());
		if (user == null) return "invalidToken";
		if (!data.getUserEmail().equals(user.getEmail())) return "unauthorized";

		data.setCreationDate(LocalDateTime.now().toString().replace('T', ' '));
		return super.add(data);
	}

	@Override
	public String remove(PostRequestData data) {
		User user = gestor.getUser(data.getToken());
		if (user == null) return null;
		if (!data.getUserEmail().equals(user.getEmail()) && user.getRole() != Role.ADMIN) return null;

		return super.remove(data);
	}

	@Override
	public List<Post> getPostsByUser(UserRequestData data) {
		User loggedUser = gestor.getUser(data.getToken());
		if (loggedUser == null) return null;

		// comprobar la igualdad de emails es una manera fiable de comparar usuarios
		if (!loggedUser.getEmail().equals(data.getEmail()) && loggedUser.getRole() != Role.ADMIN) return null;

		return super.getPostsByUser(data);
	}

	@Override
	public List<Post> getPostsInCommunity(CommunityRequestData data) {
		return super.getPostsInCommunity(data);
	}

	@Override
	public List<Post> getNewPosts(UserRequestData data) {
		User user = gestor.getUser(data.getToken());
		return super.getNewPosts(user);
	}

	@Override
	public List<Post> getPostsByUserInCommunity(MemberRequestData data) {
		return super.getPostsByUserInCommunity(data.getUser(), data.getCommunity());
	}

}
