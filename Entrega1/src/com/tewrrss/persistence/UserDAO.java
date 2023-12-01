package com.tewrrss.persistence;

import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public interface UserDAO extends DAO {

	List<User> listAll();
	Optional<User> findByEmail(String email);
	boolean remove(User user);
	boolean add(User user);
	boolean update(User user);
	List<User> getUsersInCommunity(Community community);

}
