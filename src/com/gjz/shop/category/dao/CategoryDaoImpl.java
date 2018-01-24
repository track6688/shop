package com.gjz.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gjz.shop.category.entity.Category;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {

	public List<Category> findAll() {
		
		String hql = "from Category";
		
		List<Category> list = this.getHibernateTemplate().find(hql);
		
		return list;
	}

	
	
}
