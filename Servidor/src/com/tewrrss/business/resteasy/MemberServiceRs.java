package com.tewrrss.business.resteasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.resteasy.CommunityRequestData;
import com.tewrrss.dto.resteasy.UserRequestData;

@Path("/members")
public interface MemberServiceRs {

	@GET
	@Path("/listMyJoined/{token}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Community> listMyJoined(@PathParam ("token") String token);

	@POST
	@Path("/listJoined")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Community> listJoined(UserRequestData data);

	@POST
	@Path(value = "/join")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	String join(CommunityRequestData data);

	@POST
	@Path(value = "/leave")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	String leave(CommunityRequestData data);

}
