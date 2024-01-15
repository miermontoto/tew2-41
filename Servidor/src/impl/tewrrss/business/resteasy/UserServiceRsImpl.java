package impl.tewrrss.business.resteasy;

import java.util.List;

import com.tewrrss.business.UserService;
import com.tewrrss.business.resteasy.UserServiceRs;
import com.tewrrss.dto.User;
import com.tewrrss.dto.resteasy.UserRequestData;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.UserServiceImpl;

public class UserServiceRsImpl extends UserServiceImpl implements UserServiceRs {

	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public List<User> listAll(String token) {
		User user = gestor.getUser(token);
		if (user == null || user.getRole() != Role.ADMIN) return null;

		return listAll();
	}

	@Override
	public String add(User user) {
		UserService service = new UserServiceImpl();
		String result = service.add(user);

		return result.equals("success") ? gestor.register(user) : result;
	}

	@Override
	public String batchAdd(UserRequestData user) {
		User admin = gestor.getUser(user.getToken());
		if (admin == null || admin.getRole() != Role.ADMIN) return "unauthorized";

		return this.add(user);
	}

}
