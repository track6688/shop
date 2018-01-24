package com.gjz.shop.category.service;

import java.util.List;

import com.gjz.shop.category.dao.CategoryDao;
import com.gjz.shop.category.entity.Category;

public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> findAll() {
		
		
		return categoryDao.findAll();
	}

}
