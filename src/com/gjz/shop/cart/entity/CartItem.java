package com.gjz.shop.cart.entity;

import java.io.Serializable;

import com.gjz.shop.product.entity.Product;

/**
 * �������
 * @author GuoJiazhen
 *
 */
public class CartItem implements Serializable {

	private Product product;	//�������е���Ʒ
	private  int count;		//����
	private double subtotal;	//����С��
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
	
	//С�����Զ�����ġ�
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	
}
