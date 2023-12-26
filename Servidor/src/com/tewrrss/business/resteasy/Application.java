package com.tewrrss.business.resteasy;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import impl.tewrrss.business.resteasy.CommunityServiceRsImpl;
import impl.tewrrss.business.resteasy.DatabaseServiceRsImpl;
import impl.tewrrss.business.resteasy.LoginServiceRsImpl;
import impl.tewrrss.business.resteasy.PostServiceRsImpl;
import impl.tewrrss.business.resteasy.UserServiceRsImpl;

@SuppressWarnings("unchecked")
public class Application extends javax.ws.rs.core.Application {

	private Set<Class<?>> classes = new HashSet<>();

	public Application() {
		classes.add(LoginServiceRsImpl.class);
		classes.add(UserServiceRsImpl.class);
		classes.add(CommunityServiceRsImpl.class);
		classes.add(PostServiceRsImpl.class);
		classes.add(DatabaseServiceRsImpl.class);
	}

 	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return Collections.EMPTY_SET;
	}
}
