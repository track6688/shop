package com.gjz.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gjz.shop.product.dao.ProductDao;
import com.gjz.shop.product.entity.Product;
import com.gjz.shop.utils.PageBean;

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

	@Override
	public PageBean<Product> findByPageCid(Integer cid, Integer page) {
		
		PageBean<Product> pageBean = new PageBean<>();
		//设置当前页数
		pageBean.setPage(page);
		
		int limit = 8;
		
		pageBean.setLimit(limit);
		
		int totalCount = 0;
		
		totalCount = productDao.findCountCid(cid);
		
		pageBean.setTotalCount(totalCount);
		
		int totalPage = 0;
		
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}
		else
		{
			totalPage = totalCount / limit + 1;
		}
		
		//设置总页数
		pageBean.setTotalPage(totalPage);
		
		
		
		int begin = (page - 1) * limit;
		
		//每页数据集合
		List<Product> list = productDao.findByPageCid(cid, begin, limit);
		
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public PageBean<Product> findByPageCsid(Integer csid, Integer page) {
		
		PageBean<Product> pageBean = new PageBean<>();
		
		//设置当前页数
		pageBean.setPage(page);
		
		int limit = 8;
		pageBean.setLimit(limit);
		
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		
		int totalPage = 0;
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}
		else
		{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public PageBean<Product> findByPage(Integer page) {
		
		PageBean<Product> pageBean = new PageBean<>();
		
		//设置当前页数
		pageBean.setPage(page);
		
		//每页显示记录条数
		Integer limit = 10;
		pageBean.setLimit(limit);
		
		Integer totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		Integer totalPage = 0;
		
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}
		else
		{
			totalPage = totalCount / limit + 1;
		}
		
		pageBean.setTotalPage(totalPage);
		
		Integer begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void save(Product product) {
		productDao.save(product);
	}

	@Override
	public void delete(Product product) {
		productDao.delete(product);
	}

	//业务层修改商品
	@Override
	public void update(Product product) {
		
		productDao.update(product);
		
	}
	
}
