package com.gjz.shop.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.category.entity.Category;
import com.gjz.shop.category.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Override
	public String execute() throws Exception {
		
		//��ѯ����һ���˵�
		List<Category> cList = categoryService.findAll();
		
		//��һ���˵����뵽Session�ķ�Χ
		ActionContext.getContext().getSession().put("cList", cList);
		return "index";
		
	}

}
