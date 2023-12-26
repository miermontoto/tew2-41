package impl.tewrrss.business.resteasy;

import java.util.List;

import com.tewrrss.business.resteasy.PostServiceRs;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.PostToken;
import com.tewrrss.dto.PostUserToken;
import com.tewrrss.dto.UserComToken;
import com.tewrrss.dto.UserToken;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.PostServiceImpl;

public class PostServiceRsImpl extends PostServiceImpl implements PostServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public String add(PostToken post) {
		// Impedimos que alguien cree un post en nombre de otro
		if (!gestor.checkToken(post.getToken()).equals(post.getUserEmail())) return null;
		return add(ClassCreation.createPost(post));
	}

	@Override
	public String remove(PostToken post) {
		// Impedimos que alguien borre el post de otro
		if (!gestor.checkToken(post.getToken()).equals(post.getUserEmail())) return null;
		return remove(ClassCreation.createPost(post));
	}

	@Override
	public List<Post> getPostsByUser(UserToken user) {
		String checkToken = gestor.checkToken(user.getToken());
		if (checkToken == null) return null;

		// FIXME: necesitamos comprobación de admin???
		// Optional<User> userOpt = findByEmail(checkToken);
		// if (!userOpt.isPresent()) return null;
		if (!gestor.checkToken(user.getToken()).equals(user.getEmail())) return null;
		return getPostsByUser(ClassCreation.createUser(user));
	}

	@Override
	public List<Post> getPostsInCommunity(CommunityToken community) {
		if (gestor.checkToken(community.getToken()) != null) return null;
		return getPostsInCommunity(ClassCreation.createCommunity(community));
	}

	@Override
	public List<Post> getNewPosts(UserToken user) {
		// Solo se puede usar para el usuario actual
		if (!gestor.checkToken(user.getToken()).equals(user.getEmail())) return null;
		return getNewPosts(ClassCreation.createUser(user));
	}

	@Override
	public List<Post> getPostsByUserInCommunity(UserComToken UCK) {
		// Solo se puede usar para el usuario actual
		if (!gestor.checkToken(UCK.getToken()).equals(UCK.getUser().getEmail())) return null;
		return getPostsByUserInCommunity(UCK.getUser(), UCK.getCommunity());
	}

	@Override
	public boolean ableToRemove(PostUserToken PstUsrTk) {
		if (GestorSesion.getInstance().checkToken(PstUsrTk.getToken()) == null) return false;
		return ableToRemove(PstUsrTk.getPost(), PstUsrTk.getUser());
	}

}
