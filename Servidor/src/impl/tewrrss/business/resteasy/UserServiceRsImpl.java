package impl.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotAuthorizedException;

import com.tewrrss.business.resteasy.UserServiceRs;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserToken;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.UserServiceImpl;

public class UserServiceRsImpl extends UserServiceImpl implements UserServiceRs {

	
	
	@Override
	public List<User> listAll(String token) {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().checkToken(token) != null) {
			return listAll();
		}		
		return null;
	}

	@Override
	public Optional<User> findByEmail(String email, String token)
			throws EntityNotFoundException, NotAuthorizedException {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().checkToken(token) != null) {
			return findByEmail(email);
		}
		return null;
	}

	@Override
	public String remove(UserToken user) {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().checkToken(user.getToken()) != null) {
			return remove(ClassCreation.CreateUser(user));
		}
		return null;
	}

	@Override
	public String add(UserToken user) {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().checkToken(user.getToken()) != null) {
			return add(ClassCreation.CreateUser(user));
		}
		return null;
	}

	@Override
	public String update(UserToken user) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().checkToken(user.getToken()) != null) {
			return update(ClassCreation.CreateUser(user));
		}
		return null;
	}

	@Override
	public List<User> getUsersInCommunity(CommunityToken community) {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().checkToken(community.getToken()) != null) {
			return getUsersInCommunity(ClassCreation.CreateCommunity(community));
		}
		return null;
	}




}
