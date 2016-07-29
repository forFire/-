package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.Orders;
import com.service.cache.RedisTestCache;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-application.xml" }) // 加载配置文件
public class RedisTest {

	@Autowired
	RedisTestCache redisTestCache;
	
	@Test
    public void testSet(){
		
		Orders orders = new Orders();
		orders.setId(2);
		orders.setMemo("订单描述");
		redisTestCache.addControlRoomByOrgId(String.valueOf(orders.getId()), orders);
		
		
	}
	
	
	
	
}
