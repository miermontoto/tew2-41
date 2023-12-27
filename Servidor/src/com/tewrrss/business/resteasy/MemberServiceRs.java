package com.tewrrss.business.resteasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserComToken;
import com.tewrrss.dto.UserToken;

@Path("/members")
public interface MemberServiceRs {

	@GET
	@Path(value = "/listJoined")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Community> listJoined(UserToken user);

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	String join(UserComToken UCK);

	@POST
	@Path(value = "/listJoined")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	String leave(UserComToken UCK);

	@GET
	@Path("/getUsersInCommunity")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<User> getUsersInCommunity(CommunityToken community);

	@GET
	@Path(value = "/ableToJoin")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	boolean ableToJoin(UserComToken UCK);

}
