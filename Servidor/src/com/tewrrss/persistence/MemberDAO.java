package com.tewrrss.persistence;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public interface MemberDAO extends DAO {

	boolean join(Community community, User user);
	boolean leave(Community community, User user);
	List<User> getUsersInCommunity(Community community);
	List<Community> getJoinedCommunities(User user);

}
