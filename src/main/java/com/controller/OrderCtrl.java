package com.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.model.Orders;
import com.service.OrderService;
import com.util.Page;
import com.util.Response;
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
	
	//增加
		@ResponseBody
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public Response<?> save(Orders order){
			orderService.save(order);
			return ResponseHelper.createSuccessResponse();
		}
		
		
		//根据ID查询
		@ResponseBody
		@RequestMapping(value = "/findById", method = RequestMethod.POST)
		public Orders findById(Integer id){
			return	orderService.findById(id);
		}
		
		
		//修改
		@ResponseBody
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public Response<?> update(Orders order){
			orderService.update(order);
			return ResponseHelper.createSuccessResponse();
		}
			
		//删除
		@ResponseBody
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public Response<?> delete(Orders order){
			orderService.delete(order);
			return ResponseHelper.createSuccessResponse();
		}
	
	
	
}
