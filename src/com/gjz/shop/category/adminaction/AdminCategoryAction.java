package com.gjz.shop.category.adminaction;

import java.util.List;

import com.gjz.shop.category.entity.Category;
import com.gjz.shop.category.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	
	private Category category = new Category();
	
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	@Override
	public Category getModel() {
		return category;
	}
	
	/*
	 * �������е�һ������
	 */
	public String findAll()
	{
		
		List<Category> cList = categoryService.findAll();
		
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		return "findAll";
	}

	
	/**
	 * 
	 * @return
	 */
	public String save()
	{
		categoryService.save(category);
		
		return "saveSuccess";
	}
	
	/**
	 * ɾ��һ������
	 * @return
	 */
	public String delete()
	{
		
		Category deleteCategory = categoryService.findByCid(category.getCid());
		
		categoryService.delete(deleteCategory);
		
		return "deleteSuccess";
	}
	
	/**
	 * �༭һ������
	 * @return
	 */
	public String edit()
	{
		
		//��ѯ��Ҫ�޸ĵ�Category
		category = categoryService.findByCid(category.getCid());
		
		return "editSuccess";
	}
	
	/**
	 * ����һ�����ൽ���ݿ�
	 * @return
	 */
	public String update()
	{
		categoryService.update(category);
		
		return "updateSuccess";
	}
	
}
