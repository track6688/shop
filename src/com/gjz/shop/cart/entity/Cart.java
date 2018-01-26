package com.gjz.shop.cart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ����
 * @author GuoJiazhen
 *
 */

public class Cart implements Serializable {

	
	//�������, Map��key����Ʒ��pid,value�ǹ�����
	private Map<Integer, CartItem> map = new LinkedHashMap<>();
	
	//������
	private double total;

	//����ȫ�������������
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
	
	
	//��ӹ�����
	public void addCart(CartItem cartItem)
	{
		
		//�жϹ��ﳵ���Ƿ��Ѿ����ڸù�����
		/**
		 * �������
		 * 	*��������
		 * 	�ܼ�=�ܼ�-������С��
		 * ��������ڣ�
		 * ��map����ӹ�����
		 * 	�ܼ�=�ܼ�һ������С��
		 * 
		 */
		Integer pid = cartItem.getProduct().getPid();
		
		//���жϹ��ﳵ���Ƿ��Ѿ�����������Ʒ
		if(map.containsKey(pid))
		{
			//����
			CartItem cartItem_old = map.get(pid);
			cartItem_old.setCount(cartItem_old.getCount() + cartItem.getCount());
			
		}
		else
		{
			//������
			map.put(pid, cartItem);
		}
		
		//�ܽ�������ӵĹ�����С��
		total += cartItem.getSubtotal();
		
		
	}
	
	
	
	//�ӹ��ﳵ���Ƴ�������
	public void removeCart(Integer pid)
	{
		//���������Ƴ����ﳵ
		//�ܼ� = �ܼ�-�Ƴ��Ĺ�����С��
		CartItem cartItem = map.remove(pid);
		
		total -= cartItem.getSubtotal();
	}
	
	
	//��չ��ﳵ
	public void clearCart()
	{
		//�����й�������գ����ܽ������Ϊ0
		total = 0;
		map.clear();
		
	}
	
	
	
}
