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

	@Override
	public void save(Category category) {
		
		this.getHibernateTemplate().save(category);
		
	}

	@Override
	public Category findByCid(Integer cid) {
		
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	@Override
	public void delete(Category deleteCategory) {

		this.getHibernateTemplate().delete(deleteCategory);
	}

	@Override
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

	
	
}
