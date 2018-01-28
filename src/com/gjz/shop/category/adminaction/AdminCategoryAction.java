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
	 * 查找所有的一级分类
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
	 * 删除一级分类
	 * @return
	 */
	public String delete()
	{
		
		Category deleteCategory = categoryService.findByCid(category.getCid());
		
		categoryService.delete(deleteCategory);
		
		return "deleteSuccess";
	}
	
	/**
	 * 编辑一级分类
	 * @return
	 */
	public String edit()
	{
		
		//查询出要修改的Category
		category = categoryService.findByCid(category.getCid());
		
		return "editSuccess";
	}
	
	/**
	 * 更新一级分类到数据库
	 * @return
	 */
	public String update()
	{
		categoryService.update(category);
		
		return "updateSuccess";
	}
	
}
