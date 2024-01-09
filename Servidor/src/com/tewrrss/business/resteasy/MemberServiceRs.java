package com.tewrrss.business.resteasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.dto.Community;

@Path("/members")
public interface MemberServiceRs {

	@GET
	@Path(value = "/listJoined")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Community> listJoined(String token);

	@POST
	@Path(value = "/join")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	String join(String token, Community com);

	@POST
	@Path(value = "/listJoined")
	@Consumes({ MediaType.TEXT_PLAIN })
	String leave(String token, Community com);

	@GET
	@Path(value = "/ableToJoin")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	boolean ableToJoin(String token, Community com);

}
