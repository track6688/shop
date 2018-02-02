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
	
	//当前页数
	Integer page ;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	/**
	 * 查找全部订单
	 * @return
	 */
	public String findAll()
	{
		
		PageBean<Order> pageBean = orderService.findByPage(page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	
	/**
	 * 通过订单id查找订单Item
	 * @return
	 */
	public String findOrderItem()
	{
		
		List<OrderItem> oiList = orderService.findOrderItem(order.getOid());
		
		ActionContext.getContext().getValueStack().set("oiList", oiList);
		
		return "findOrderItem";
	}
	
	//后台修改订单状态的方法
	public String updateState()
	{
		//1、根据订单id查询订单
		
		Order currOrder = orderService.findByOid(order.getOid());
		
		//2、修改订单状态
		currOrder.setState(3);
		
		orderService.update(currOrder);
		
		//3、页面跳转
		return "updateStateSuccess";
	}
	
	

}
