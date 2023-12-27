package com.tewrrss.business.resteasy;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.UserComToken;
import com.tewrrss.dto.UserToken;

@Path("/CommunityServiceRs")
public interface CommunityServiceRs {

	@GET
	@Path(value = "/listAll")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Community> listAll();

	@GET
	@Path(value = "/listJoined")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Community> listJoined(UserToken user);

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String join(UserComToken UCK);

	@POST
	@Path(value = "/listJoined")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String leave(UserComToken UCK);

	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String create(CommunityToken comunidad);

	@DELETE
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String remove(CommunityToken comunidad);

	@GET
	@Path(value = "/ableToJoin")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	boolean ableToJoin(UserComToken UCK);

	@GET
	@Path(value = "/findByName/{userName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Community findByName(@PathParam(value = "userName") String name, String token);

	@GET
	@Path(value = "/search/{search}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Community> search(@PathParam(value = "search") String search, String Token);

}
