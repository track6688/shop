package com.gjz.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gjz.shop.user.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
	
	public User findByUserName(String username)
	{
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			
			return list.get(0);
			
		}
		return null;
	}

	//注册用户
	@Override
	public void save(User user) {
		
		
		this.getHibernateTemplate().save(user);
		
	}

	@Override
	public void update(User existUser) {
		
		this.getHibernateTemplate().update(existUser);
		
	}

	/**
	 * 通过code查找用户
	 */
	@Override
	public User findByCode(String code) {
		
		String hql = "from User where code = ?";
		
		List<User> list = this.getHibernateTemplate().find(hql, code);
		
		if(list != null && list.size() > 0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}	
	}

	/**
	 * 通过查找用户，实现登录
	 */
	@Override
	public User login(User user) {
		
		String hql = "from User where username = ? and password = ? and state = ?";
		
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(), user.getPassword(), 1);
		
		if(list != null && list.size() > 0)
		{
			return list.get(0);
		}
		
		return null;
	}
}
