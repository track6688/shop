package com.gjz.shop.order.service;

import java.util.List;

import com.gjz.shop.order.entity.Order;
import com.gjz.shop.order.entity.OrderItem;
import com.gjz.shop.utils.PageBean;

public interface OrderService {

	void save(Order order);

	PageBean<Order> findByUid(Integer uid, Integer page);

	Order findByOid(Integer oid);

	void update(Order currentOrder);


	PageBean<Order> findByPage(Integer page);

	List<OrderItem> findOrderItem(Integer oid);


}
