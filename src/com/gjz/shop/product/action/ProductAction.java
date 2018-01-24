package com.gjz.shop.product.action;

import com.gjz.shop.product.entity.Product;
import com.gjz.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();
	
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
	@Override
	public Product getModel() {
		return product;
	}
	
	//根据商品的ID进行查询商品
	public String findByPid()
	{
		
		product = productService.findByPid(product.getPid());
		
		return "findByPid";
	}
	
	
	/**
	 * 通过Cid查找商品
	 * @return
	 */
	public String findByCid()
	{
		return "findByCid";
	}
	
	
	
	
}
