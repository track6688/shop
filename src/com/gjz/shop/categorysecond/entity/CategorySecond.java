package com.gjz.shop.categorysecond.entity;

import com.gjz.shop.category.entity.Category;

/**
 * ���������ʵ����
 * @author GuoJiazhen
 *
 */
public class CategorySecond {

	private Integer csid;
	private String csname;
	
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
	
	
	
	
}
