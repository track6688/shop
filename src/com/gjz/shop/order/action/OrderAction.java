package com.gjz.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.cart.entity.Cart;
import com.gjz.shop.cart.entity.CartItem;
import com.gjz.shop.order.entity.Order;
import com.gjz.shop.order.entity.OrderItem;
import com.gjz.shop.order.service.OrderService;
import com.gjz.shop.user.entity.User;
import com.gjz.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��������Action
 * @author GuoJiazhen
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	Order order = new Order();
		
	private OrderService orderService;

	private Integer page;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//ģ������
	@Override
	public Order getModel() {
		return order;
	}

	/**
	 * ���ɶ���
	 * @return
	 */
	public String createOrder()
	{
		
		order.setOrdertime(new Date());
		order.setState(1);   //1��δ֧��	2��֧��δ���� 3��֧��������    4����ɶ���
		
		Cart cart = (Cart) ServletActionContext.getRequest()
				.getSession().getAttribute("cart");
		
		if(cart == null)
		{
			this.addActionMessage("���������Ʒ��");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		
		for (CartItem cartItem : cart.getCartItems()) {
			
			OrderItem orderItem = new OrderItem();
			
			orderItem.setCount(cartItem.getCount());
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubtotal());
			
			order.getOrderItems().add(orderItem);
		}
		
		User user = (User) ServletActionContext.getRequest()
				.getSession().getAttribute("existUser");
		
		if(user == null)
		{
			this.addActionError("�ף�����û�е�¼Ӵ��");
			return "login";
		}
		
		order.setUser(user);
		
		orderService.save(order);
		
		return "createOrder";
	}
	
	
	/**
	 * ͨ��Uid�����û��Ķ���
	 * @return
	 */
	public String findByUid()
	{
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		PageBean<Order> pageBean = orderService.findByUid(user.getUid(), page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByUid";
	}
	
	
}
