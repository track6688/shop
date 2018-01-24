package com.gjz.shop.product.action;

import com.gjz.shop.product.entity.Product;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();
	
	@Override
	public Product getModel() {
		return product;
	}
	
	//根据商品的ID进行查询商品
	public String findByPid()
	{
		return NONE;
	}
	
	
	
}
