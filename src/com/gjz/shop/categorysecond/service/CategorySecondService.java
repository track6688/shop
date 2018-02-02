package com.gjz.shop.categorysecond.service;

import java.util.List;

import com.gjz.shop.categorysecond.entity.CategorySecond;
import com.gjz.shop.utils.PageBean;

public interface CategorySecondService {

	PageBean<CategorySecond> findByPage(Integer page);

	void save(CategorySecond categorySecond);

	CategorySecond findByCsid(Integer csid);

	void delete(CategorySecond categorySecond);

	void update(CategorySecond categorySecond);

	List<CategorySecond> findAll();

}
