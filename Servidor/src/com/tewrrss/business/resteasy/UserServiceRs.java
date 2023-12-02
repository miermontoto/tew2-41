package com.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.business.UserService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserToken;

public interface UserServiceRs extends UserService{

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<User> listAll(String token);
	
	@GET
	// paramentro indicado en la URL, utilizado el m�todo con @PathParam
	@Path("{id}")
	// formato en el que los datos se retornan en el m�todo
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Optional<User> findByEmail(@PathParam("id") String email, String token) throws EntityNotFoundException, NotAuthorizedException;;
	
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String remove(UserToken user);
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String add(UserToken user);
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String update(UserToken user) throws EntityNotFoundException;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
	List<User> getUsersInCommunity(CommunityToken community);

}
