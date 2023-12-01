package com.tewrrss.business;

import com.tewrrss.dto.User;

public interface LoginService {

	User verify(String username, String pass);
	boolean emailExists(String email);
	void register(User user);

}
