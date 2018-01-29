package com.gjz.shop.categorysecond.dao;

import java.util.List;

import com.gjz.shop.categorysecond.entity.CategorySecond;

public interface CategorySecondDao {

	Integer findCount();

	List<CategorySecond> findByPage(Integer begin, Integer limit);

	void save(CategorySecond categorySecond);

	CategorySecond findByCsid(Integer csid);

	void delete(CategorySecond categorySecond);

	void update(CategorySecond categorySecond);

}
