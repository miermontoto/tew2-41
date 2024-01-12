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
import com.tewrrss.dto.Post;
import com.tewrrss.dto.resteasy.*;

@Path("/posts")
public interface PostServiceRs extends PostService {

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	String add(PostRequestData data);

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	String remove(PostRequestData data);

	@GET
	@Path("/getPostsByUser")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Post> getPostsByUser(UserRequestData data);

	@GET
	@Path("/getPostsByUserInCommunity")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Post> getPostsByUserInCommunity(MemberRequestData data);

	@GET
	@Path("/getPostsInCommunity")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Post> getPostsInCommunity(CommunityRequestData data);

	@GET
	@Path("/getNewPosts")
	@Produces({ MediaType.TEXT_PLAIN })
	List<Post> getNewPosts(UserRequestData data);

}
