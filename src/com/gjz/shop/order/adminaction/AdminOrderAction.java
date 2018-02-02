package com.gjz.shop.order.adminaction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.order.entity.Order;
import com.gjz.shop.order.entity.OrderItem;
import com.gjz.shop.order.service.OrderService;
import com.gjz.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	private Order order = new Order();
	
	@Override
	public Order getModel() {
		return order;
	}
	
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//��ǰҳ��
	Integer page ;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	/**
	 * ����ȫ������
	 * @return
	 */
	public String findAll()
	{
		
		PageBean<Order> pageBean = orderService.findByPage(page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	
	/**
	 * ͨ������id���Ҷ���Item
	 * @return
	 */
	public String findOrderItem()
	{
		
		List<OrderItem> oiList = orderService.findOrderItem(order.getOid());
		
		ActionContext.getContext().getValueStack().set("oiList", oiList);
		
		return "findOrderItem";
	}
	
	//��̨�޸Ķ���״̬�ķ���
	public String updateState()
	{
		//1�����ݶ���id��ѯ����
		
		Order currOrder = orderService.findByOid(order.getOid());
		
		//2���޸Ķ���״̬
		currOrder.setState(3);
		
		orderService.update(currOrder);
		
		//3��ҳ����ת
		return "updateStateSuccess";
	}
	
	

}
