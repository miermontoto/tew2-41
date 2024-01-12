package com.tewrrss.infrastructure;

import com.tewrrss.business.ServicesFactory;
import com.tewrrss.persistence.PersistenceFactory;

import impl.tewrrss.business.ServicesFactoryImpl;
import impl.tewrrss.persistence.SimplePersistenceFactory;

public class Factories {

	public static final ServicesFactory services = new ServicesFactoryImpl();
	public static final PersistenceFactory persistence = new SimplePersistenceFactory();

	private Factories() { }

}
