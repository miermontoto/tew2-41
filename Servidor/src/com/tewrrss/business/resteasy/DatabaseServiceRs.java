package com.tewrrss.business.resteasy;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/database")
public interface DatabaseServiceRs {

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	boolean reset(String token);

}
