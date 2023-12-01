package impl.tewrrss.business.resteasy;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotAuthorizedException;

import com.tewrrss.business.resteasy.UserServiceRs;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.CommunityToken;
import com.tewrrss.dto.User;
import com.tewrrss.dto.UserToken;
import com.tewrrss.infrastructure.GestorSesion;

import impl.tewrrss.business.users.Add;
import impl.tewrrss.business.users.FindByEmail;
import impl.tewrrss.business.users.GetUsersInCommunity;
import impl.tewrrss.business.users.ListAll;
import impl.tewrrss.business.users.Remove;
import impl.tewrrss.business.users.Update;

public class UserServiceRsImpl implements UserServiceRs{

	private User CreateUser(UserToken user) {
		return new User(user.getEmail(), user.getUsername(), user.getPassword(), user.getRole());
	}
	private Community CreateCommunity(CommunityToken com) {
		return new Community(com.getName(), com.getDescription());
	}
	
	
	
	@Override
	public List<User> listAll(String token) {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().comprobarToken(token) != null) {
			return listAll();
		}		
		return null;
	}

	@Override
	public Optional<User> findByEmail(String email, String token)
			throws EntityNotFoundException, NotAuthorizedException {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().comprobarToken(token) != null) {
			return findByEmail(email);
		}
		return null;
	}

	@Override
	public String remove(UserToken user) {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().comprobarToken(user.getToken()) != null) {
			return remove(CreateUser(user));
		}
		return null;
	}

	@Override
	public String add(UserToken user) {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().comprobarToken(user.getToken()) != null) {
			return add(CreateUser(user));
		}
		return null;
	}

	@Override
	public String update(UserToken user) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().comprobarToken(user.getToken()) != null) {
			return update(CreateUser(user));
		}
		return null;
	}

	@Override
	public List<User> getUsersInCommunity(CommunityToken community) {
		// TODO Auto-generated method stub
		if (GestorSesion.getInstance().comprobarToken(community.getToken()) != null) {
			return getUsersInCommunity(CreateCommunity(community));
		}
		return null;
	}
	@Override
	public List<User> listAll() {
		// TODO Auto-generated method stub
		return new ListAll().listAll();
	}
	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return new FindByEmail().findByEmail(email);
	}
	@Override
	public String remove(User user) {
		// TODO Auto-generated method stub
		return new Remove().remove(user);
	}
	@Override
	public String add(User user) {
		// TODO Auto-generated method stub
		return new Add().add(user);
	}
	@Override
	public String update(User user) {
		// TODO Auto-generated method stub
		return new Update().update(user);
	}
	@Override
	public List<User> getUsersInCommunity(Community community) {
		// TODO Auto-generated method stub
		return new GetUsersInCommunity().getUsersInCommunity(community);

	}


}
