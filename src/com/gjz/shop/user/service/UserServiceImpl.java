package com.gjz.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.gjz.shop.user.dao.UserDao;
import com.gjz.shop.user.entity.User;
import com.gjz.shop.utils.UUIDUtils;

@Transactional
public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public User findByUsername(String username) {
		return userDao.findByUserName(username);
	}


	@Override
	public void save(User user) {
		
		user.setState(0); //0:未激活 1、用户已经激活
		
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		
		userDao.save(user);
	}

	
}
