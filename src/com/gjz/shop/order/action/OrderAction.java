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
 * ��������Action
 * @author GuoJiazhen
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	Order order = new Order();
		
	private OrderService orderService;

	private Integer page;
	
	//֧��ͨ��
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
	
	
	/**
	 * ͨ�������Ų��Ҷ���
	 * @return
	 */
	public String findByOid()
	{
		
		order = orderService.findByOid(order.getOid());
		
		return "findByOid";
	}
	
	
	/**
	 * ����֧��
	 * @return
	 * @throws IOException 
	 */
	public String payOrder() throws IOException
	{
		System.out.println("֧������");
		//1���޸Ķ�����Ϣ�������յ��ջ�����Ϣ
		
		Order currentOrder = orderService.findByOid(order.getOid());
		
		currentOrder.setAddr(order.getAddr());
		currentOrder.setName(order.getName());
		currentOrder.setPhone(order.getPhone());
		
		orderService.update(currentOrder);
		
		// 2�����֧��
		// ������Ҫ�Ĳ���:
		String p0_Cmd = "Buy"; // ҵ������:
		String p1_MerId = "10001126856";// �̻����:
		String p2_Order = order.getOid().toString();// �������:
		String p3_Amt = "0.01"; // ������:
		String p4_Cur = "CNY"; // ���ױ���:
		String p5_Pid = ""; // ��Ʒ����:
		String p6_Pcat = ""; // ��Ʒ����:
		String p7_Pdesc = ""; // ��Ʒ����:
		String p8_Url = "http://192.168.0.104:8080/MyShop/order_callBack.action"; // �̻�����֧���ɹ����ݵĵ�ַ:
		String p9_SAF = ""; // �ͻ���ַ:
		String pa_MP = ""; // �̻���չ��Ϣ:
		String pd_FrpId = this.pd_FrpId;// ֧��ͨ������:
		String pr_NeedResponse = "1"; // Ӧ�����:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // ��Կ
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// ���ױ���������:
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
	 * ֧���������
	 * @return
	 */
	public String callBack()
	{
		//1���޸Ķ���״̬���޸�״̬�Ѿ�����
		
		Order currentOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		
		currentOrder.setState(2);
		orderService.update(currentOrder);
		
		this.addActionMessage("����֧���ɹ���������ţ�" + r6_Order + " ����Ľ�" + r3_Amt);
		
		//2����ת��֧���ɹ�ҳ��
		return "msg";
	}
	
	//���Ķ���״̬
	public String updateState()
	{
		//1����ѯ��Ʒ
		
		Order currOrder = orderService.findByOid(order.getOid());
		
		//2���޸���Ϣ
		
		currOrder.setState(4);
		orderService.update(currOrder);
		//3��ҳ����ת
		
		return "updateStateSuccess";
	}
	
	
}
