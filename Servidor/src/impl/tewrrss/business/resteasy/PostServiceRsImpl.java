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

	/** Crea un nuevo post
	 */
	@Override
	public String add(PostToken post) {
		//Impedimos que alguien cree un post en nombre de otro
		if (GestorSesion.getInstance().checkToken(post.getToken()).equals(post.getUserEmail())) {
			return add(ClassCreation.createPost(post));
		}
		return null;
	}

	/** Borra un post del usuario 
	 * */
	@Override
	public String remove(PostToken post) {
		//Impedimos que alguien borre el post de otro
		if (GestorSesion.getInstance().checkToken(post.getToken()).equals(post.getUserEmail()) ) {
			return remove(ClassCreation.createPost(post)) ;
		}
		return null;
	}

	/** Obtiene los post del usuario dado 
	 * */
	@Override
	public List<Post> getPostsByUser(UserToken user) {
		//
		if (GestorSesion.getInstance().checkToken(user.getToken()).equals(user.getEmail())) {
			return getPostsByUser(ClassCreation.CreateUser(user)) ;
		}
		return null;
	}

	/** Obtiene los post de la comunidad dada
	 * */
	@Override
	public List<Post> getPostsInCommunity(CommunityToken community) {
		if (GestorSesion.getInstance().checkToken(community.getToken()) != null) {
			return getPostsInCommunity(ClassCreation.CreateCommunity(community)) ;
		}
		return null;
	}

	/**  Obtiene los 5 ultimos Post de las comunidades a las que pertenece el usuario
	 * */
	@Override
	public List<Post> getNewPosts(UserToken user) {
		// Solo se puede usar para el usuario actual
		if (GestorSesion.getInstance().checkToken(user.getToken()).equals(user.getEmail())) {
			return getNewPosts(ClassCreation.CreateUser(user)) ;
		}
		return null;
	}

	/**  Obtiene los post del usuario dado en la comunidad dada
	 * */
	@Override
	public List<Post> getPostsByUserInCommunity(UserComToken UCK) {
		// Solo se puede usar para el usuario actual
		if (GestorSesion.getInstance().checkToken(UCK.getToken()).equals(UCK.getUser().getEmail())) {
			return getPostsByUserInCommunity(UCK.getUser(), UCK.getCommunity());
		}
		return null;
	}

	/** Devuelve cierto si el usuario es dueño del post o admin (No usar en rest, verifica con email) 
	 * */
	@Override
	public boolean ableToRemove(PostUserToken PstUsrTk) {
		if (GestorSesion.getInstance().checkToken(PstUsrTk.getToken()) != null) {
			return ableToRemove(PstUsrTk.getPost(), PstUsrTk.getUser()) ;
		}
		return false;
	}

	
}
