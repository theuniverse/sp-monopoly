package com.iiiss.spring.common;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao implements IDao {
	@Autowired
	protected IPersistenceManager persistenceManager;

	public void setPersistenceManager(IPersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}
}
