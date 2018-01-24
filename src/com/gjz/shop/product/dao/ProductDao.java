package com.gjz.shop.product.dao;

import java.util.List;

import com.gjz.shop.product.entity.Product;

public interface ProductDao {
	
	public List<Product> findHot();

	public List<Product> findNew();
	
	
}
