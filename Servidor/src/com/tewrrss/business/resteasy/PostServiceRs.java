package com.tewrrss.business.resteasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.business.PostService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

@Path("/posts")
public interface PostServiceRs extends PostService {

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	String add(String token, Post post);

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	String remove(String token, Post post);

	@GET
	@Path("/getPostsByUser")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Post> getPostsByUser(String token, User targetUser);

	@GET
	@Path("/getPostsByUserInCommunity")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Post> getPostsByUserInCommunity(User user, Community com);

	@GET
	@Path("/getPostsInCommunity")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Post> getPostsInCommunity(Community com);

	@GET
	@Path("/getNewPosts")
	@Produces({ MediaType.TEXT_PLAIN })
	List<Post> getNewPosts(String token);

}
