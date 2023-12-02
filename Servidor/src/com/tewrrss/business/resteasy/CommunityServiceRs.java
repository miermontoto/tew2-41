package com.tewrrss.business.resteasy;
import java.util.List;
import java.util.Optional;

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
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserComToken;
import com.tewrrss.dto.UserToken;

public interface CommunityServiceRs {

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Community> listAll();

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
	List<Community> listJoined(UserToken user);
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String join(UserComToken UCK);
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
	String leave(UserComToken UCK);
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String create(CommunityToken comunidad);
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String remove(CommunityToken comunidad);
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	boolean ableToJoin(UserComToken UCK);
	
	@GET
	@Path(value = "{userName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Optional<Community> findByName(@PathParam(value = "userName") String name, String token);
	
	@GET
	@Path(value = "{search}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Community> search(@PathParam(value = "search") String search);
	
}
