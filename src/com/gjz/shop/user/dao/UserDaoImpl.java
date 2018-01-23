package com.gjz.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gjz.shop.user.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
	
	public User findByUserName(String username)
	{
		System.out.println("��ѯ�û���>>>>" + username);
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			
			System.out.println("��ѯ���û���--->" + list);
			return list.get(0);
			
		}
		System.out.println("�޷���ѯ���û���");
		return null;
	}

	//ע���û�
	@Override
	public void save(User user) {
		
		this.getHibernateTemplate().save(user);
		
	}
}
