package com.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.OrderService;
import com.util.Page;
import com.util.ResponseHelper;

/**
 *订单管理 
 */
@Controller
@RequestMapping(value = "/orders")
public class OrderCtrl {
	private static final Logger logger = LoggerFactory.getLogger(OrderCtrl.class);

	@Autowired
	OrderService orderService;
	
	/**
	 *订单查询 
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(Page page){
		logger.info("查询-------------------------");
		page = orderService.listAll(page);
		return ResponseHelper.createSuccessResponse(page);
	}
	
	
	
	
	
}
