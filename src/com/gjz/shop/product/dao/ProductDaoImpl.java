package com.gjz.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gjz.shop.product.entity.Product;

public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao{

	@Override
	public List<Product> findHot() {
		
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		//查询热门的商品，条件就是is_hot = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		
		//按时间倒序输出
		criteria.addOrder(Order.desc("pdate"));
		
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

	@Override
	public List<Product> findNew() {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		criteria.addOrder(Order.desc("pdate"));
		
		List<Product> nList = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return nList;
	}

	@Override
	public Product findByPid(Integer pid) {
		
		return this.getHibernateTemplate().get(Product.class, pid);
		
	}

}
