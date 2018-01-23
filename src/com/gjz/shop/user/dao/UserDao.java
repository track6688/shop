package com.gjz.shop.user.dao;

import com.gjz.shop.user.entity.User;

public interface UserDao {

	public User findByUserName(String username);

	public void save(User user);
	
	
}
