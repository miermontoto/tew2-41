package impl.tewrrss.business.resteasy;

import java.util.Optional;

import com.tewrrss.business.resteasy.DatabaseServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.DatabaseServiceImpl;
import impl.tewrrss.business.UserServiceImpl;

public class DatabaseServiceRsImpl extends DatabaseServiceImpl implements DatabaseServiceRs {

	@Override
	public boolean reset(String token) {
		UserServiceImpl check = new UserServiceImpl();
		String checkToken = GestorSesion.getInstance().checkToken(token);
		if (checkToken == null) return false;

		Optional<User> user = check.findByEmail(checkToken);
		if (!user.isPresent() || user.get().getRole() != Role.ADMIN) return false;

		return reset();
	}

}
