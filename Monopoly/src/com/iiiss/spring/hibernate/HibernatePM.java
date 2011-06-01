package com.iiiss.spring.hibernate;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.iiiss.spring.common.IBean;
import com.iiiss.spring.common.IPersistenceManager;

@Transactional
public class HibernatePM extends HibernateDaoSupport implements
		IPersistenceManager {
	private ApplicationContext context = new ClassPathXmlApplicationContext(
			"/monopoly/hibernate/beans/beans.xml");

	@SuppressWarnings("unchecked")
	private Class getImplClass(String beanName) {
		return context.getBean(beanName).getClass();
	}

	public IBean newObject(String beanName) {
		// scope="prototype"
		IBean bean = (IBean) context.getBean(beanName);
		getHibernateTemplate().persist(bean);
		return bean;
	}

	@SuppressWarnings("unchecked")
	public <T extends IBean> T getObject(String beanName, long id) {
		return (T) getHibernateTemplate().get(getImplClass(beanName), id);
	}

	public <T extends IBean> T getObject(String beanName, String query) {
		List<T> objectList = getObjects(beanName, query);
		return objectList.isEmpty() ? null : objectList.get(0);
	}

	@SuppressWarnings("unchecked")
	public <T extends IBean> List<T> getObjects(String beanName, String query) {
		query = query == null ? "" : query;
		return getHibernateTemplate().find(
				"from " + getImplClass(beanName).getName() + " " + query);
	}

	public void deteleObject(IBean object) {
		getHibernateTemplate().delete(object);
	}
}
