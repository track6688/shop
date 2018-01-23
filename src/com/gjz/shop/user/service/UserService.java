package com.gjz.shop.user.service;

import com.gjz.shop.user.entity.User;

public interface UserService {
	
	public User findByUsername(String username);

	public void save(User user);

	public User findByCode(String code);

	public void update(User existUser);

	public User login(User user);
}
