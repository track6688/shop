package com.gjz.shop.cart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 * @author GuoJiazhen
 *
 */

public class Cart implements Serializable {

	
	//购物项集合, Map的key是商品的pid,value是购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<>();
	
	//购物金额
	private double total;

	//返回全部购物项的内容
	public Collection<CartItem> getCartItems()
	{
		return map.values();
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	//添加购物项
	public void addCart(CartItem cartItem)
	{
		
		//判断购物车中是否已经存在该购物项
		/**
		 * 如果存在
		 * 	*数量增加
		 * 	总计=总计-购物项小计
		 * 如果不存在：
		 * 向map中添加购物项
		 * 	总计=总计一购物项小计
		 * 
		 */
		Integer pid = cartItem.getProduct().getPid();
		
		//先判断购物车中是否已经存在这种商品
		if(map.containsKey(pid))
		{
			//存在
			CartItem cartItem_old = map.get(pid);
			cartItem_old.setCount(cartItem_old.getCount() + cartItem.getCount());
			
		}
		else
		{
			//不存在
			map.put(pid, cartItem);
		}
		
		//总金额加上添加的购物项小计
		total += cartItem.getSubtotal();
		
		
	}
	
	
	
	//从购物车中移除购物项
	public void removeCart(Integer pid)
	{
		//将购物项移除购物车
		//总计 = 总计-移除的购物项小计
		CartItem cartItem = map.remove(pid);
		
		total -= cartItem.getSubtotal();
	}
	
	
	//清空购物车
	public void clearCart()
	{
		//将所有购物项清空，将总金额设置为0
		total = 0;
		map.clear();
		
	}
	
	
	
}
