package com.tewrrss.business;

import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public interface CommunityService {

	List<Community> listAll();
	List<Community> listJoined(User user);
	String join(Community community, User user);
	String leave(Community community, User user);
	String create(Community comunidad);
	String remove(Community comunidad);
	boolean ableToJoin(Community comunidad, User user);
	Optional<Community> findByName(String name);
	List<Community> search(String search);

}
