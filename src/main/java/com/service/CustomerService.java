package com.service;

import com.model.Customers;
import com.util.Page;

@SuppressWarnings("rawtypes")
public interface CustomerService {
	void save(Customers customers);
	Page listAll(Page page);
	public Customers findById(Integer id);
    void update(Customers customers);
    void delete(Customers customers);
}
