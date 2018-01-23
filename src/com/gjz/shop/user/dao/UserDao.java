package com.gjz.shop.user.dao;

import com.gjz.shop.user.entity.User;

public interface UserDao {

	public User findByUserName(String username);

	public void save(User user);

	public void update(User existUser);

	public User findByCode(String code);

	/**
	 * µÇÂ¼
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	
}
