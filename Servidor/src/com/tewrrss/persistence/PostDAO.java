package com.tewrrss.persistence;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

public interface PostDAO extends DAO {

	List<Post> getPostsInCommunity(Community community);
	List<Post> getPostsByUser(User user);
	List<Post> getPostsInCommunitiesOfUser(User user);
	List<Post> getPostsByUserInCommunity(User user, Community community);
	List<Post> searchPosts(String search);
	boolean remove(Post post);
	boolean add(Post post);
	boolean update(Post post);

}
