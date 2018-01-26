package com.gjz.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.cart.entity.Cart;
import com.gjz.shop.cart.entity.CartItem;
import com.gjz.shop.product.entity.Product;
import com.gjz.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CartAction extends ActionSupport{
	
	//用来接收pid
	private Integer pid;
	//用来接收count
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
	 * 将购物项添加到购物车
	 * @return
	 */
	public String addCart()
	{	
		//封装一个CartItem对象
		CartItem cartItem = new CartItem();
		
		cartItem.setCount(count);
		//根据pid查找商品
		
		Product product = productService.findByPid(pid);
		//设置商品
		cartItem.setProduct(product);
		
		//将购物项添加到购物车
		//购物车存放在session中
		Cart cart = getCart();
		cart.addCart(cartItem);
		
		return "addCart";
	}

	/*
	 * 从session中获取购物车
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
	 * 清空购物车
	 */
	
	public String clearCart()
	{
		
		Cart cart = getCart();
		
		cart.clearCart();
		
		return "clearCart";
	}
	
	/**
	 * 从购物车中移除商品
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
	 * 跳转到我的购物车
	 */
	public String myCart()
	{
		return "myCart";
	}

}
