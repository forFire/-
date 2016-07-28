package com.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.dao.baseDao.GenericDao;
import com.model.Orders;
import com.util.Page;

@Repository
public class OrderDao extends GenericDao<Orders, Serializable>{

	public Page listAll(Page page){
		String hql ="from Orders";
		return queryResultByPage(hql,page);
	}
	
	
	public void saveOrder(Orders orders){
		save(orders);
	}
	
}
