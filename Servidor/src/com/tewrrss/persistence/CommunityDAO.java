package com.tewrrss.persistence;

import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.Community;

public interface CommunityDAO extends DAO {

	boolean add(Community community);
	boolean remove(Community community);
	boolean update(Community community);
	List<Community> getCommunities();
	Optional<Community> findByName(String name);
	List<Community> search(String search);

}
