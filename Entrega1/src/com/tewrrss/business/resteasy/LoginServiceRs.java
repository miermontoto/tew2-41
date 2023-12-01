package com.tewrrss.business.resteasy;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.dto.User;

public interface LoginServiceRs {
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.TEXT_PLAIN })
	String login(User user);

}
