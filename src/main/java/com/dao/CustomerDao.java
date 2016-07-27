package com.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.baseDao.GenericDao;
import com.model.Customers;
import com.util.Page;
@Repository
public class CustomerDao extends GenericDao<Customers, Serializable>{

	
	public void saveCustomer(Customers customers){
		save(customers);
	}
	
	public Page listAll(Page page){
		String hql ="from Customers";
		return queryResultByPage(hql,page);
	}
	
	
}
