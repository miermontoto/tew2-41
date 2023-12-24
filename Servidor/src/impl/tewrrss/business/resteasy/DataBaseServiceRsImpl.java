package impl.tewrrss.business.resteasy;

import com.tewrrss.business.resteasy.DatabaseServiceRs;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.DatabaseServiceImpl;

public class DataBaseServiceRsImpl extends DatabaseServiceImpl implements DatabaseServiceRs{

	@Override
	public boolean reset(String token) {
		// TODO Auto-generated method stub
		impl.tewrrss.business.UserServiceImpl check = new impl.tewrrss.business.UserServiceImpl();
		if (check.findByEmail(GestorSesion.getInstance().checkToken(token)).get().getRole() == 0) {
			return reset();
		}
		return false;
	}

}