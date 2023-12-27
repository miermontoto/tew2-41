package impl.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import com.tewrrss.business.resteasy.CommunityServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.User;
import com.tewrrss.infrastructure.GestorSesion;
import com.tewrrss.util.Role;

import impl.tewrrss.business.CommunityServiceImpl;
import impl.tewrrss.business.UserServiceImpl;

public class CommunityServiceRsImpl extends CommunityServiceImpl implements CommunityServiceRs {

	private UserServiceImpl userImpl = new UserServiceImpl();
	private GestorSesion gestor = GestorSesion.getInstance();

	@Override
	public String create(CommunityToken comunidad) {
		// Cualquier usuario registrado puede crearla
		if (gestor.checkToken(comunidad.getToken()) == null) {
			return create(ClassCreation.createCommunity(comunidad));
		}

		return null;
	}

	@Override
	public String remove(CommunityToken comunidad) {
		String tokenCheck = gestor.checkToken(comunidad.getToken());
		if (tokenCheck == null) return null;

		Optional<User> user = userImpl.findByEmail(tokenCheck);
		if (!user.isPresent()) return null;
		if (user.get().getRole() != Role.ADMIN) return null; // Solo un administrador puede borrar una comunidad

		return remove(ClassCreation.createCommunity(comunidad));
	}

	@Override
	public Community findByName(String name, String token) {
		if (gestor.checkToken(token) == null) return null;

		Optional<Community> com = findByName(name);
		return com.isPresent() ? com.get() : null;
	}

	@Override
	public List<Community> search(String search, String Token) {
		if (gestor.checkToken(Token) == null) return null;
		return search(search);
	}
}
