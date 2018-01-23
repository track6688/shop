package com.gjz.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gjz.shop.user.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
	
	public User findByUserName(String username)
	{
		System.out.println("查询用户名>>>>" + username);
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			
			System.out.println("查询到用户名--->" + list);
			return list.get(0);
			
		}
		System.out.println("无法查询到用户名");
		return null;
	}

	//注册用户
	@Override
	public void save(User user) {
		
		this.getHibernateTemplate().save(user);
		
	}
}
