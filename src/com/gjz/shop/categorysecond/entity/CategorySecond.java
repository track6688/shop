package com.gjz.shop.categorysecond.entity;

import java.io.Serializable;
import java.util.Set;

import com.gjz.shop.category.entity.Category;
import com.gjz.shop.product.entity.Product;

/**
 * 二级分类的实体类
 * @author GuoJiazhen
 *
 */
public class CategorySecond implements Serializable{

	private Integer csid;
	private String csname;
	
	private Set<Product> products;
	
	
	private Category category;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
	
}
