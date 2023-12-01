package com.tewrrss.business;

import java.util.List;

import com.tewrrss.dto.Community;
import com.tewrrss.dto.Post;
import com.tewrrss.dto.User;

public interface PostService {

	String add(Post post);
	String remove(Post post);
	List<Post> getPostsByUser(User user);
	List<Post> getPostsInCommunity(Community community);
	List<Post> getNewPosts(User user);
	List<Post> getPostsByUserInCommunity(User user, Community community);
	boolean ableToRemove(Post post, User user);

}
