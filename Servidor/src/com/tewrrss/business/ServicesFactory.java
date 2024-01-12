package com.tewrrss.business;

public interface ServicesFactory {

	LoginService createLoginService();
	DatabaseService createDatabaseService();
	CommunityService createCommunityService();
	UserService createUserService();
	PostService createPostService();
	MemberService createMemberService();

}
