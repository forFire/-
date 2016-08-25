package com.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.model.Customers;
import com.service.CustomerService;
import com.util.Page;
import com.util.Response;
import com.util.ResponseHelper;


/**
 *客户Ctrl 
 */
@Controller
@RequestMapping(value = "/customers")
public class CustomersCtrl {
	private static final Logger logger = LoggerFactory.getLogger(CustomersCtrl.class);
	@Autowired
	CustomerService customerService;
	
	//查询
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(Page page){
		logger.info("查询-------------------------");
		page = customerService.listAll(page);
		return ResponseHelper.createSuccessResponse(page);
	}
	
	
	//增加
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Response<?> save(Customers customers){
		customerService.save(customers);
		return ResponseHelper.createSuccessResponse();
	}
	
	
	//根据ID查询
	@ResponseBody
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public Customers findById(Integer id){
		return	customerService.findById(id);
	}
	
	
	
	//修改
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Response<?> update(Customers customers){
		customerService.update(customers);
		return ResponseHelper.createSuccessResponse();
	}
		
	//删除
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Response<?> delete(Customers customers){
		customerService.delete(customers);
		return ResponseHelper.createSuccessResponse();
	}
	
	
	
}
