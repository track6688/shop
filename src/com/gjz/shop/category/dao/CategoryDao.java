package com.gjz.shop.category.dao;

import java.util.List;

import com.gjz.shop.category.entity.Category;

public interface CategoryDao {

	List<Category> findAll();

	void save(Category category);

	Category findByCid(Integer cid);

	void delete(Category deleteCategory);

	void update(Category category);


}
