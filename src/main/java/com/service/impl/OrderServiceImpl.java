package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderDao;
import com.model.Orders;
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

	@Override
	public void save(Orders orders) {
		orderDao.saveOrder(orders);
	}

	@Override
	public void update(Orders orders) {
		Orders c = orderDao.get_(orders.getId(), Orders.class);
		c.setName(orders.getName());
		
		
		
		orderDao.update_(c);
	}

	@Override
	public void delete(Orders orders) {
		orderDao.del_(orders);

	}

	@Override
	public Orders findById(Integer id) {
		return orderDao.get_(id, Orders.class);
	}
}
