package com.gjz.shop.product.action;

import java.util.List;

import com.gjz.shop.product.entity.Product;
import com.gjz.shop.product.service.ProductService;
import com.gjz.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();
	
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private Integer cid;
	private Integer csid;

	private Integer page;		//��ǰҳ��
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}
	
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	
	public Integer getCsid() {
		return csid;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	
	@Override
	public Product getModel() {
		return product;
	}
	
	//������Ʒ��ID���в�ѯ��Ʒ
	public String findByPid()
	{
		
		product = productService.findByPid(product.getPid());
		
		return "findByPid";
	}
	
	
	/**
	 * ͨ��Cid������Ʒ
	 * @return
	 */
	public String findByCid()
	{
		
		PageBean<Product> pb = productService.findByPageCid(cid, page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pb);
		
		return "findByCid";
	}
	
	/**
	 * ͨ��CSid������Ʒ
	 * @return
	 */
	public String findByCsid()
	{
		
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);;
		
		return "findByCsid";
	}
	
	
	
	
}
