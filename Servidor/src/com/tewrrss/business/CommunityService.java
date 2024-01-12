package com.tewrrss.business;

import java.util.List;
import java.util.Optional;

import com.tewrrss.dto.Community;

public interface CommunityService {

	List<Community> listAll();
	String create(Community comunidad);
	String remove(Community comunidad);
	Optional<Community> findByName(String name);
	List<Community> search(String search);

}
