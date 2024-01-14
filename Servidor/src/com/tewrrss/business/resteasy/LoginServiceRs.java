package com.tewrrss.business.resteasy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.dto.User;

@Path("/login")
public interface LoginServiceRs {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	String login(User user);

	@GET
	@Path("/logout/{token}")
	@Produces({ MediaType.TEXT_PLAIN })
	String logout(@PathParam("token") String token);

	@GET
	@Path("/myUser/{token}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	User myUser(@PathParam("token") String token);

}
