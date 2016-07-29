package com.service.cache;

import java.util.Map;
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
	
	
	/**
	 *hashMap 
	 */
	private static final String HASH_MAP = "hash_map";
	
	
	//放入map 先组装《key,value》
	public void setMap(Map<String, String> map){
		redisTemplate.opsForHash().putAll(HASH_MAP , map);
	}
	
	//加减1 对map里的key操作
	public void incrementValue( String key, int num){
		redisTemplate.opsForHash().increment(HASH_MAP,  key, num);
	}
	
	//取出 id 为 key 可以是map 里的key  也可以是 加减1的那个key
	public Integer getValue(String id){
		Object num = redisTemplate.opsForHash().get(HASH_MAP, id);
		if(num == null)
			return 0;
		return Integer.parseInt(num.toString());
	}
	
	//删除
	public void delMap(){
		redisTemplate.delete(HASH_MAP);;
	}
	
	
	
	
	
	
}
