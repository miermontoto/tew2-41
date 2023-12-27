package com.tewrrss.business;

import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.User;

public interface UserService {

	List<User> listAll();
	Optional<User> findByEmail(String email);
	String remove(User user);
	String add(User user);
	String update(User user);

}
