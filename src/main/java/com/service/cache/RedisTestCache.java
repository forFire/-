package com.service.cache;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.model.Orders;
import com.util.JsonUtil;

/**
 *
 */
@Component
public class RedisTestCache {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	private static final String ORDER = "order_";
	
	
	
	/**
	 *set 无序去重
	 */
	public void addControlRoomByOrgId(String orderId, Orders orders) {
		 redisTemplate.opsForSet().add(ORDER+orderId,JsonUtil.toJson(orders));
	}
	
	public Set<Object> getControlRoomByOrgId(String orderId) {
		return  redisTemplate.opsForSet().members(ORDER+orderId);
	}
	
	public void removeControlRoomByOrgId(String orderId,Orders orders) {
		redisTemplate.opsForSet().remove(ORDER+orderId,JsonUtil.toJson(orders));
	}
	
	
	
	
	
	
	
}
