package com.gjz.shop.order.service;

import java.util.List;

import com.gjz.shop.order.dao.OrderDao;
import com.gjz.shop.order.entity.Order;
import com.gjz.shop.order.entity.OrderItem;
import com.gjz.shop.utils.PageBean;

/**
 * 业务逻辑层
 * @author GuoJiazhen
 *
 */
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public void save(Order order) {
		
		orderDao.save(order);
		
	}

	@Override
	public PageBean<Order> findByUid(Integer uid, Integer page) {
		
		PageBean<Order> pageBean = new PageBean<>();
		
		//设置当前页数
		pageBean.setPage(page);
		
		//设置每页记录数
		Integer limit = 5;
		pageBean.setLimit(limit);
		
		//设置记录的总条数，需要查数据库
		Integer totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		
		//设置记录的总页数，通过记录总条数可计算
		Integer totalPage = null;
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}
		else
		{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		//设置当前页面的所有订单，需要查数据库
		Integer begin = (page -1) * limit;
		
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	
	
}
