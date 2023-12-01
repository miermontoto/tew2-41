package com.tewrrss.persistence;

public interface PersistenceFactory {

	UserDAO getUserDAO();
	CommunityDAO getCommunityDAO();
	PostDAO getPostDAO();

}
