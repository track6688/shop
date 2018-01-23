package com.gjz.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.gjz.shop.user.dao.UserDao;
import com.gjz.shop.user.entity.User;
import com.gjz.shop.utils.MailUtils;
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
		
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(), user.getCode());
	}


	@Override
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}


	@Override
	public void update(User existUser) {
		userDao.update(existUser);
	}


	@Override
	public User login(User user) {
		
		return userDao.login(user);
		
		
	}

	
}
