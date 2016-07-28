package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderDao;
import com.service.OrderService;
import com.util.Page;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;
	
	@Override
	public Page listAll(Page page) {
		return orderDao.listAll(page);
	}

}
