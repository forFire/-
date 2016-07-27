package com.dao.baseDao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author lk
 */
public class HibernateBaseDao extends HibernateDaoSupport {
	
	public void setSysSssionFactory(Object sysSssionFactory) {
		this.setSessionFactory((SessionFactory) sysSssionFactory);
	}

	public Session getCurrentSession() {
		return this.getSessionFactory().getCurrentSession(); //
	}

}