package com.tewrrss.persistence;

import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.User;

public interface CommunityDAO extends DAO {

	boolean add(Community community);
	boolean remove(Community community);
	boolean update(Community community);
	List<Community> getCommunities();
	List<Community> getJoinedCommunities(User user);
	boolean join(Community community, User user);
	boolean leave(Community community, User user);
	Optional<Community> findByName(String name);
	List<Community> search(String search);

}
