package com.gjz.shop.user.service;

import com.gjz.shop.user.entity.User;

public interface UserService {
	
	public User findByUsername(String username);

	public void save(User user);
}
