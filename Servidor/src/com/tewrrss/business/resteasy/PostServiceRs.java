package com.tewrrss.business.resteasy;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.business.PostService;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.PostToken;
import com.tewrrss.dto.UserComToken;
import com.tewrrss.dto.PostUserToken;
import com.tewrrss.dto.UserToken;
public interface PostServiceRs extends PostService{

	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String add(PostToken post);
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String remove(PostToken post);
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
	List<Post> getPostsByUser(UserToken user);
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Post> getPostsInCommunity(CommunityToken community);
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
	List<Post> getNewPosts(UserToken user);
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Post> getPostsByUserInCommunity(UserComToken UCK);
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	boolean ableToRemove(PostUserToken PstUsrTk);

}
