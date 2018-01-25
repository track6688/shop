package com.gjz.shop.product.service;

import java.util.List;

import com.gjz.shop.product.entity.Product;
import com.gjz.shop.utils.PageBean;

public interface ProductService {
	
	public List<Product> findHot();

	public List<Product> findNew();

	public Product findByPid(Integer pid);

	public PageBean<Product> findByPageCid(Integer cid, Integer page);

	public PageBean<Product> findByPageCsid(Integer csid, Integer page);
	
}
