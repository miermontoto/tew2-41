package com.tewrrss.business.resteasy;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.tewrrss.dto.PostToken;

@Path("/DBServiceRs")
public interface DatabaseServiceRs {

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	boolean reset (String token);

}