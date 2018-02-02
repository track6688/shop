package com.gjz.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gjz.shop.categorysecond.entity.CategorySecond;
import com.gjz.shop.utils.PageHibernateCallback;

public class CategorySecondDaoImpl extends HibernateDaoSupport implements CategorySecondDao {

	@Override
	public Integer findCount() {
		
		String hql = "select count(*) from CategorySecond";
		
		List<Long> list = this.getHibernateTemplate().find(hql);
		
		if(list != null & list.size() > 0)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@Override
	public List<CategorySecond> findByPage(Integer begin, Integer limit) {
		
		String hql = "from CategorySecond cs order by cs.csid desc";
		
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		
		return list;
	}

	@Override
	public void save(CategorySecond categorySecond) {
		
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * Dao层查找二级分类
	 */
	@Override
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	@Override
	public void delete(CategorySecond categorySecond) {

		this.getHibernateTemplate().delete(categorySecond);
		
	}

	@Override
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	@Override
	public List<CategorySecond> findAll() {
		
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	

}
