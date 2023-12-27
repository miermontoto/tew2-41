package com.tewrrss.business;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public interface MemberService {

	List<Community> listJoined(User user);
	String join(Community community, User user);
	String leave(Community community, User user);
	List<User> getUsersInCommunity(Community community);
	boolean ableToJoin(Community comunidad, User user);

}
