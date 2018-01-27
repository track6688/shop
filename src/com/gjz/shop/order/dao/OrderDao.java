package com.gjz.shop.order.dao;

import java.util.List;

import com.gjz.shop.order.entity.Order;

public interface OrderDao {

	void save(Order order);

	Integer findCountByUid(Integer uid);

	List<Order> findByPageUid(Integer uid, Integer begin, Integer limit);

	Order findByOid(Integer oid);

	void update(Order currentOrder);

}
