package impl.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import com.tewrrss.business.resteasy.CommunityServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.UserComToken;
import com.tewrrss.dto.UserToken;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.CommunityServiceImpl;

public class CommunityServiceRsImpl extends CommunityServiceImpl implements CommunityServiceRs{

	@Override
	public List<Community> listJoined(UserToken user) {
		if (GestorSesion.getInstance().checkToken(user.getToken()) != null) {
			return listJoined(ClassCreation.CreateUser(user));
		}
		return null;
		}

	@Override
	public String join(UserComToken UCK) {

		if (GestorSesion.getInstance().checkToken(UCK.getToken()) != null) {
			return join(UCK.getCommunity(),  UCK.getUser());
		}
		return null;
		}

	@Override
	public String leave(UserComToken UCK) {

		if (GestorSesion.getInstance().checkToken(UCK.getToken()) != null) {
			return leave(UCK.getCommunity(), UCK.getUser());
		}
		return null;
		}

	@Override
	public String create(CommunityToken comunidad) {

		if (GestorSesion.getInstance().checkToken(comunidad.getToken()) != null) {
			return create(ClassCreation.CreateCommunity(comunidad));
		}
		return null;
	}

	@Override
	public String remove(CommunityToken comunidad) {

		if (GestorSesion.getInstance().checkToken(comunidad.getToken()) != null) {
			return remove(ClassCreation.CreateCommunity(comunidad));
		}
		return null;
	}

	@Override
	public boolean ableToJoin(UserComToken UCK) {

		if (GestorSesion.getInstance().checkToken(UCK.getToken()) != null) {
			return ableToJoin(UCK.getCommunity(), UCK.getUser());
		}
		return false;
	}

	@Override
	public Optional<Community> findByName(String name, String token) {

		if (GestorSesion.getInstance().checkToken(token) != null) {
			return findByName(name);
		}
		return null;
	}


	@Override
	public List<Community> search(String search, String Token) {

		if (GestorSesion.getInstance().checkToken(Token) != null) {
			return search(search);
		}

		return null;
	}

}
