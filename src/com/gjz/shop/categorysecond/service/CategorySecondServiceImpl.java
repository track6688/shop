package com.gjz.shop.categorysecond.service;

import java.util.List;

import com.gjz.shop.category.dao.CategoryDao;
import com.gjz.shop.categorysecond.dao.CategorySecondDao;
import com.gjz.shop.categorysecond.entity.CategorySecond;
import com.gjz.shop.utils.PageBean;

public class CategorySecondServiceImpl implements CategorySecondService {
	
	private CategorySecondDao categorySecondDao;
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	@Override
	public PageBean<CategorySecond> findByPage(Integer page) {
		
		PageBean<CategorySecond> pageBean = new PageBean<>();
		
		//设置当前页数
		pageBean.setPage(page);
		
		Integer limit = 10;
		pageBean.setLimit(limit);
		
		Integer totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		Integer totalPage = 0;
		
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}
		else
		{
			totalPage = totalCount / limit + 1;
		}
		
		pageBean.setTotalPage(totalPage);
		
		Integer begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin, limit);
		
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * 保存二级分类
	 * 逻辑层
	 */
	@Override
	public void save(CategorySecond categorySecond) {
		
		categorySecondDao.save(categorySecond);
		
	}

	/**
	 * 逻辑层查找CategorySecond
	 */
	@Override
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	/**
	 * 逻辑层删除二级分类
	 */
	@Override
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	@Override
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
	
	
	

}
