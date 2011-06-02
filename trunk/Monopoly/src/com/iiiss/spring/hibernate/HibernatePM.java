package com.iiiss.spring.hibernate;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.iiiss.spring.common.IBean;
import com.iiiss.spring.common.IPersistenceManager;

/*
 * Monopoly - com.iiiss.spring.hibernate.HibernatePM.java
 * 
 * Copyright (C) 2011  Mingye Cheng <Cheng, Mingye at GMAIL>
 * 
 * HibernatePM.java is part of <iiiStudio Template Framework> project.
 * This project is hosted at <http://iiiss-template-ssh.googlecode.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
