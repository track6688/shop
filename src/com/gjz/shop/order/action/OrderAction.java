package com.gjz.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.cart.entity.Cart;
import com.gjz.shop.cart.entity.CartItem;
import com.gjz.shop.order.entity.Order;
import com.gjz.shop.order.entity.OrderItem;
import com.gjz.shop.order.service.OrderService;
import com.gjz.shop.user.entity.User;
import com.gjz.shop.utils.PageBean;
import com.gjz.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单管理Action
 * @author GuoJiazhen
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	Order order = new Order();
		
	private OrderService orderService;

	private Integer page;
	
	//支付通道
	private String pd_FrpId;
	
	private String r6_Order;
	private String r3_Amt;
	
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//模型驱动
	@Override
	public Order getModel() {
		return order;
	}

	/**
	 * 生成订单
	 * @return
	 */
	public String createOrder()
	{
		
		order.setOrdertime(new Date());
		order.setState(1);   //1、未支付	2、支付未发货 3、支付并发货    4、完成订单
		
		Cart cart = (Cart) ServletActionContext.getRequest()
				.getSession().getAttribute("cart");
		
		if(cart == null)
		{
			this.addActionMessage("请先添加商品！");
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
			this.addActionError("亲！您还没有登录哟！");
			return "login";
		}
		
		order.setUser(user);
		
		orderService.save(order);
		
		return "createOrder";
	}
	
	
	/**
	 * 通过Uid查找用户的订单
	 * @return
	 */
	public String findByUid()
	{
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		PageBean<Order> pageBean = orderService.findByUid(user.getUid(), page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByUid";
	}
	
	
	/**
	 * 通过订单号查找订单
	 * @return
	 */
	public String findByOid()
	{
		
		order = orderService.findByOid(order.getOid());
		
		return "findByOid";
	}
	
	
	/**
	 * 订单支付
	 * @return
	 * @throws IOException 
	 */
	public String payOrder() throws IOException
	{
		System.out.println("支付！！");
		//1、修改订单信息，如最终的收货人信息
		
		Order currentOrder = orderService.findByOid(order.getOid());
		
		currentOrder.setAddr(order.getAddr());
		currentOrder.setName(order.getName());
		currentOrder.setPhone(order.getPhone());
		
		orderService.update(currentOrder);
		
		// 2、完成支付
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://192.168.0.104:8080/MyShop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		ServletActionContext.getResponse().sendRedirect(sb.toString());
		
		return NONE;
	}
	
	/**
	 * 支付结果返回
	 * @return
	 */
	public String callBack()
	{
		//1、修改订单状态：修改状态已经付款
		
		Order currentOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		
		currentOrder.setState(2);
		orderService.update(currentOrder);
		
		this.addActionMessage("订单支付成功：订单编号：" + r6_Order + " 付款的金额：" + r3_Amt);
		
		//2、跳转到支付成功页面
		return "msg";
	}
	
	//更改订单状态
	public String updateState()
	{
		//1、查询商品
		
		Order currOrder = orderService.findByOid(order.getOid());
		
		//2、修改信息
		
		currOrder.setState(4);
		orderService.update(currOrder);
		//3、页面跳转
		
		return "updateStateSuccess";
	}
	
	
}
