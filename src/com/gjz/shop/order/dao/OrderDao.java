package com.gjz.shop.order.dao;

import java.util.List;

import com.gjz.shop.order.entity.Order;
import com.gjz.shop.order.entity.OrderItem;

public interface OrderDao {

	void save(Order order);

	Integer findCountByUid(Integer uid);

	List<Order> findByPageUid(Integer uid, Integer begin, Integer limit);

	Order findByOid(Integer oid);

	void update(Order currentOrder);

	Integer findCount();

	List<Order> findByPage(Integer page, Integer limit);

	List<OrderItem> findOrderItem(Integer oid);

}
