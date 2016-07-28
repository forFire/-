package com.service;

import com.model.Orders;
import com.util.Page;

public interface OrderService {
	void save(Orders orders);
	Page listAll(Page page);
	public Orders findById(Integer id);
    void update(Orders orders);
    void delete(Orders orders);
}
