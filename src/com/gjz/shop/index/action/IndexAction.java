package com.gjz.shop.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.category.entity.Category;
import com.gjz.shop.category.service.CategoryService;
import com.gjz.shop.product.entity.Product;
import com.gjz.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	
	private CategoryService categoryService;
	
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Override
	public String execute() throws Exception {
		
		//查询所有一级菜单
		List<Category> cList = categoryService.findAll();
		
		//将一级菜单存入到Session的范围
		ActionContext.getContext().getSession().put("cList", cList);
		
		List<Product> hList = productService.findHot();
		
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询最新商品
		List<Product> nList = productService.findNew();
		
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
		
	}

}
