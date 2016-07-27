package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerDao;
import com.model.Customers;
import com.service.CustomerService;
import com.util.Page;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	@Override
	public void save(Customers customer) {
		customerDao.saveCustomer(customer);
	}

	
	@Override
	public Page listAll(Page page) {
		return	customerDao.listAll(page);
	}


	@Override
	public void update(Customers customers) {
		
		Customers c = customerDao.get_(customers.getId(),Customers.class );
		c.setSex(customers.getSex());
		c.setAddress(customers.getAddress());
		c.setName(customers.getName());
		
		customerDao.update_(c);
	}


	@Override
	public void delete(Customers customers) {
		customerDao.del_(customers);
		
	}


	@Override
	public Customers findById(Integer id) {
		return customerDao.get_(id,Customers.class );
	}
	
	
	

}
