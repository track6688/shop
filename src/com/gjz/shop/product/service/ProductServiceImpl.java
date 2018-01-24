package com.gjz.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gjz.shop.product.dao.ProductDao;
import com.gjz.shop.product.entity.Product;

@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> findHot() {
		return productDao.findHot();
	}

	@Override
	public List<Product> findNew() {
		return productDao.findNew();
	}

	@Override
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	
}
