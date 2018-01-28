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

	@Override
	public void save(Category category) {

		categoryDao.save(category);
	}

	@Override
	public Category findByCid(Integer cid) {
		
		return categoryDao.findByCid(cid);
	}

	@Override
	public void delete(Category deleteCategory) {
		categoryDao.delete(deleteCategory);
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

}
