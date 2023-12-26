package com.tewrrss.business.resteasy;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tewrrss.dto.User;

@Path("/LoginServiceRs")
public interface LoginServiceRs {
	
	/* Método POST, para realizar un login de usuarios.
	 * Recibe un JSON del lado del cliente, que aquí decodificará.
	 * ¿Cómo se convierte el JSON al usuario? -> En el cliente y el servidor ha de tener las mismas.
	 */
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.TEXT_PLAIN })
	String login(User user);

	@DELETE
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	String logon(String user);
		
}
