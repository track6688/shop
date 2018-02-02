package com.gjz.shop.order.service;

import java.util.List;

import com.gjz.shop.order.dao.OrderDao;
import com.gjz.shop.order.entity.Order;
import com.gjz.shop.order.entity.OrderItem;
import com.gjz.shop.utils.PageBean;

/**
 * ҵ���߼���
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
		
		//���õ�ǰҳ��
		pageBean.setPage(page);
		
		//����ÿҳ��¼��
		Integer limit = 5;
		pageBean.setLimit(limit);
		
		//���ü�¼������������Ҫ�����ݿ�
		Integer totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		
		//���ü�¼����ҳ����ͨ����¼�������ɼ���
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
		
		//���õ�ǰҳ������ж�������Ҫ�����ݿ�
		Integer begin = (page -1) * limit;
		
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	@Override
	public void update(Order currentOrder) {
		
		orderDao.update(currentOrder);
		
	}

	/**
	 * ҵ����ҳ���Ҷ���
	 */
	@Override
	public PageBean<Order> findByPage(Integer page) {

		PageBean<Order> pageBean = new PageBean<>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		
		Integer limit = 10;
		pageBean.setLimit(limit);
		
		Integer totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		Integer totalPage = 0;
		
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}
		else
		{
			totalPage = totalCount / limit + 1;
		}
		//������ҳ��
		pageBean.setTotalPage(totalPage);
		
		List<Order> list = orderDao.findByPage(page, limit);
		
		pageBean.setList(list);
		
		
		return pageBean;
	}

	@Override
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}

	
	
}
