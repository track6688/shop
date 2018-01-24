package com.gjz.shop.product.service;

import java.util.List;

import com.gjz.shop.product.entity.Product;

public interface ProductService {
	
	public List<Product> findHot();

	public List<Product> findNew();

	public Product findByPid(Integer pid);
	
}
