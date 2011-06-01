package com.iiiss.spring.common;

import java.util.List;

public interface IPersistenceManager {

	public IBean newObject(String beanName);

	public <T extends IBean> T getObject(String beanName, long id);

	public <T extends IBean> T getObject(String beanName, String query);

	public <T extends IBean> List<T> getObjects(String beanName, String query);

	public void deteleObject(IBean object);

}
