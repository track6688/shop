package com.gjz.shop.product.dao;

import java.util.List;

import com.gjz.shop.product.entity.Product;

public interface ProductDao {
	
	public List<Product> findHot();

	public List<Product> findNew();

	public Product findByPid(Integer pid);

	public int findCountCid(Integer cid);

	public List<Product> findByPageCid(Integer cid, int begin, int limit);

	public int findCountCsid(Integer csid);

	public List<Product> findByPageCsid(Integer csid, int begin, int limit);

	public Integer findCount();

	public List<Product> findByPage(Integer begin, Integer limit);

	public void save(Product product);

	public void delete(Product product);

	public void update(Product product);
	
	
}
