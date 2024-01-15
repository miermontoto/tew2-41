package impl.tewrrss.business.resteasy;

import com.tewrrss.business.resteasy.DatabaseServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.DatabaseServiceImpl;

public class DatabaseServiceRsImpl extends DatabaseServiceImpl implements DatabaseServiceRs {

	@Override
	public String reset(String token) {
		User user = GestorSesion.getInstance().getUser(token);
		if (user == null || user.getRole() != Role.ADMIN) return "unauthorized";

		return super.reset() ? "success" : "error";
	}

}
