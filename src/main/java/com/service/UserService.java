package com.service;

import com.model.User;

public interface UserService {

	
	public void saveUser(User user);
	public User findByUserName(String userName);
	
	void update(User user);
}
