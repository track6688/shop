package com.gjz.shop.order.service;

import com.gjz.shop.order.entity.Order;
import com.gjz.shop.utils.PageBean;

public interface OrderService {

	void save(Order order);

	PageBean<Order> findByUid(Integer uid, Integer page);

	Order findByOid(Integer oid);

	void update(Order currentOrder);


}
