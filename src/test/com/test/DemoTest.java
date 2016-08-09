package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.Customers;
import com.mysql.fabric.xmlrpc.base.Array;
import com.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-application.xml" }) // 加载配置文件
public class DemoTest {
	
	@Test
	public void test1() {
		System.out.println("12313");
		
	}
	
	@Resource
	CustomerService customerService;
	
	@Test
	public void customerTest(){
		Customers customers = new Customers();
		customers.setId(1);
		customers.setName("test");
		customerService.update(customers);
		
	}
	
	@Test
	public  void listTest() {
//		List list = new ArrayList();
//		list.add(1);
//		list.add(2);
//		Object [] obj = list.toArray();
//		for(Object obj1 : obj){
//			System.out.println(obj1.toString());
//		}
		
		Object [] obj = new Object[6];
		obj[0] = 0;
		obj[1] = 1;
		obj[2] = 2;
		obj[3] = 3;
		obj[4] = 4;
		obj[5] = 5;
		
		Object [] obj1 =  Arrays.copyOf(obj, 2);  
		
		for(Object obj2 : obj1){
			System.out.println(obj2.toString());
     	}
		
	}

	
}
