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

public class PostServiceRsImpl extends PostServiceImpl implements PostServiceRs{

	
	@Override
	public String add(PostToken post) {
		if (GestorSesion.getInstance().checkToken(post.getToken()) != null) {
			return add(ClassCreation.createPost(post));
		}
		return null;
	}

	@Override
	public String remove(PostToken post) {
		if (GestorSesion.getInstance().checkToken(post.getToken()) != null) {
			return remove(ClassCreation.createPost(post)) ;
		}
		return null;
	}

	@Override
	public List<Post> getPostsByUser(UserToken user) {
		if (GestorSesion.getInstance().checkToken(user.getToken()) != null) {
			return getPostsByUser(ClassCreation.CreateUser(user)) ;
		}
		return null;
	}

	@Override
	public List<Post> getPostsInCommunity(CommunityToken community) {
		if (GestorSesion.getInstance().checkToken(community.getToken()) != null) {
			return getPostsInCommunity(ClassCreation.CreateCommunity(community)) ;
		}
		return null;
	}

	@Override
	public List<Post> getNewPosts(UserToken user) {
		if (GestorSesion.getInstance().checkToken(user.getToken()) != null) {
			return getNewPosts(ClassCreation.CreateUser(user)) ;
		}
		return null;
	}

	@Override
	public List<Post> getPostsByUserInCommunity(UserComToken UCK) {
		if (GestorSesion.getInstance().checkToken(UCK.getToken()) != null) {
			return getPostsByUserInCommunity(UCK.getUser(), UCK.getCommunity());
		}
		return null;
	}

	@Override
	public boolean ableToRemove(PostUserToken PstUsrTk) {
		if (GestorSesion.getInstance().checkToken(PstUsrTk.getToken()) != null) {
			return ableToRemove(PstUsrTk.getPost(), PstUsrTk.getUser()) ;
		}
		return false;
	}

	
}
