package com.tewrrss.business.resteasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.business.UserService;
import com.tewrrss.dto.User;
import com.tewrrss.dto.resteasy.UserRequestData;

@Path("/users")
public interface UserServiceRs extends UserService {

	@GET
	@Path("/list")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<User> listAll(String token);

	@Override
	@PUT
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	String add(User user);

	@PUT
	@Path("/batch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	String batchAdd(UserRequestData user);

	@PUT
	@Path("/update")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.TEXT_PLAIN })
	String update(UserRequestData user);

}
