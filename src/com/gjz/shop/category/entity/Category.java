package com.gjz.shop.category.entity;

import java.io.Serializable;
import java.util.Set;

import com.gjz.shop.categorysecond.entity.CategorySecond;

public class Category implements Serializable{
	
	private Integer cid;
	private String cname;
	
	private Set<CategorySecond> categorySeconds;
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
	

}
