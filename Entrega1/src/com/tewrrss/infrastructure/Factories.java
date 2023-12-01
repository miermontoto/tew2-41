package com.tewrrss.infrastructure;

import impl.tewrrss.business.ServicesFactoryImpl;
import impl.tewrrss.persistence.SimplePersistenceFactory;

import com.tewrrss.business.ServicesFactory;
import com.tewrrss.persistence.PersistenceFactory;

public class Factories {

	public static final ServicesFactory services = new ServicesFactoryImpl();
	public static final PersistenceFactory persistence = new SimplePersistenceFactory();

	private Factories() { }

}
