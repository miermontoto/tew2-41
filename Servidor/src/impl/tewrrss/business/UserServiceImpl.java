package impl.tewrrss.business;


import java.util.List;
import java.util.Optional;

import com.tewrrss.business.UserService;
import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

import impl.tewrrss.business.users.*;

public class UserServiceImpl implements UserService {

	@Override
	public List<User> listAll() {
		return new ListAll().listAll();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return new FindByEmail().findByEmail(email);
	}

	@Override
	public String remove(User user) {
		return new Remove().remove(user);
	}

	@Override
	public String add(User user) {
		return new Add().add(user);
	}

	@Override
	public String update(User user) {
		return new Update().update(user);
	}

	@Override
	public List<User> getUsersInCommunity(Community community) {
		return new GetUsersInCommunity().getUsersInCommunity(community);
	}

}
