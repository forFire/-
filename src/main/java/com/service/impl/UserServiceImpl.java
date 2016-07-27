package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByUserName(String userName) {
		 List<User> list = userDao.findUserList(userName);
		 if(list.size() > 0){
			 return list.get(0);
		 }else{
			 return null;
		 }
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

}
