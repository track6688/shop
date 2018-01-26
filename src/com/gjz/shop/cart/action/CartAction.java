package com.gjz.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.cart.entity.Cart;
import com.gjz.shop.cart.entity.CartItem;
import com.gjz.shop.product.entity.Product;
import com.gjz.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CartAction extends ActionSupport{
	
	//��������pid
	private Integer pid;
	//��������count
	private Integer count;
	
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	/**
	 * ����������ӵ����ﳵ
	 * @return
	 */
	public String addCart()
	{	
		//��װһ��CartItem����
		CartItem cartItem = new CartItem();
		
		cartItem.setCount(count);
		//����pid������Ʒ
		
		Product product = productService.findByPid(pid);
		//������Ʒ
		cartItem.setProduct(product);
		
		//����������ӵ����ﳵ
		//���ﳵ�����session��
		Cart cart = getCart();
		cart.addCart(cartItem);
		
		return "addCart";
	}

	/*
	 * ��session�л�ȡ���ﳵ
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		
		if(cart == null)
		{
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		
		return cart;
	}
	
	/**
	 * ��չ��ﳵ
	 */
	
	public String clearCart()
	{
		
		Cart cart = getCart();
		
		cart.clearCart();
		
		return "clearCart";
	}
	
	/**
	 * �ӹ��ﳵ���Ƴ���Ʒ
	 * @return
	 */
	public String removeCartItem()
	{
		
		Cart cart = getCart();
		//cart.getCartItems();
		cart.removeCart(pid);
		
		return "removeCartItem";
	}
	
	
	/**
	 * ��ת���ҵĹ��ﳵ
	 */
	public String myCart()
	{
		return "myCart";
	}

}
