package com.gjz.shop.cart.entity;

import java.io.Serializable;

import com.gjz.shop.product.entity.Product;

/**
 * 购物对象
 * @author GuoJiazhen
 *
 */
public class CartItem implements Serializable {

	private Product product;	//购物项中的商品
	private  int count;		//数量
	private double subtotal;	//购物小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	//小计是自动计算的。
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	
}
