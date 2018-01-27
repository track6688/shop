package com.gjz.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gjz.shop.order.entity.Order;
import com.gjz.shop.utils.PageHibernateCallback;

/**
 * 订单模块Dao层的代码
 * @author GuoJiazhen
 *
 */
public class OrderDaoImpl  extends HibernateDaoSupport implements OrderDao{

	@Override
	public void save(Order order) {
		
		this.getHibernateTemplate().save(order);
		
	}

	@Override
	public Integer findCountByUid(Integer uid) {
		
		String hql = "select count(*) from Order o where o.user.uid = ?";
		
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@Override
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		
		return list;
	}

}
