package com.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dao.baseDao.GenericDao;
import com.model.User;
import com.util.Common;

@Repository
public class UserDao extends GenericDao<User, Serializable>{

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserList(String userName){
		
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuffer sb = new StringBuffer("from User where 1=1 ");
		if(!Common.isEmpty(userName)){
			sb.append(" and userName like ? ");
		}
		
		System.out.println(sb.toString()+userName);
		
		return	(List<User> )query(sb.toString(),"%"+userName+"%");
		
	}
	
	
}
