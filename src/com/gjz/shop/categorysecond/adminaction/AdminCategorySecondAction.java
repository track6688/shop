package com.gjz.shop.categorysecond.adminaction;

import java.util.List;

import com.gjz.shop.category.entity.Category;
import com.gjz.shop.category.service.CategoryService;
import com.gjz.shop.categorysecond.entity.CategorySecond;
import com.gjz.shop.categorysecond.service.CategorySecondService;
import com.gjz.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	private CategorySecond categorySecond = new CategorySecond();
	
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
	
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	/**
	 * 查找所有二级分类
	 * @return
	 */
	public String findAll()
	{
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addPage()
	{
		
		List<Category> list = categoryService.findAll();
		
		ActionContext.getContext().getValueStack().set("cList", list);
		
		return "addPage";
	}
	
	
	/**
	 * 保存二级分类
	 * @return
	 */
	public String save()
	{
		
		categorySecondService.save(categorySecond);
		
		return "saveSuccess";
	}
	
	/*
	 * 删除二级分类
	 */
	public String delete()
	{
		CategorySecond currentCategorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		
		categorySecondService.delete(currentCategorySecond);
		
		return "deleteSuccess";
	}
	
	/**
	 * 修改二级分类
	 * @return
	 */
	public String edit()
	{
		//根据二级分类id查询二级分类的对象
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		
		//查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		return "editSuccess";
		
	}
	
	/**
	 * 更新二级分类
	 * @return
	 */
	public String update()
	{
		categorySecondService.update(categorySecond);
		
		return "updateSuccess";
	}

	
}
