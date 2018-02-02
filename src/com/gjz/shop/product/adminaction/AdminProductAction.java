package com.gjz.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.gjz.shop.categorysecond.entity.CategorySecond;
import com.gjz.shop.categorysecond.service.CategorySecondService;
import com.gjz.shop.product.entity.Product;
import com.gjz.shop.product.service.ProductService;
import com.gjz.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport  implements ModelDriven<Product>{

	
	private Product product = new Product();
	
	@Override
	public Product getModel() {
		return product;
	}
	
	private File upload;	//上传的文件
	private String uploadFileName;	//文件名
	private String uploadContentType;	//文件类型
	
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	
	public void setUpload(File upload) {
		this.upload = upload;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	
	/**
	 * 查找所有的商品
	 */
	public String findAll()
	{
		PageBean<Product> pageBean =  productService.findByPage(page);
		
		//往值栈里面放入pageBean
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	/*
	 * 跳转到增加页面
	 */
	public String addPage()
	{
		
		List<CategorySecond> csList =  categorySecondService.findAll();
		
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "addPage";
	}
	
	/**
	 * 保存商品
	 * @return
	 * @throws IOException 
	 */
	public String save() throws IOException
	{
		product.setPdate(new Date());
		
		System.out.println(upload);
		
		if(upload != null)
		{
			//绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			
			//创建一个文件
			File diskFile = new File(realPath + "//" + uploadFileName);
			
			//另存为文件
			FileUtils.copyFile(upload, diskFile);
			
			product.setImage("products/" + uploadFileName);
			
		}
		
		productService.save(product);
		
		return "saveSuccess";
	}
	
	
	/**
	 * 删除商品
	 * @return
	 */
	public String delete()
	{
		product = productService.findByPid(product.getPid());
		
		if(product.getImage() != null && !product.getImage().equals(""))
		{
			String path = ServletActionContext.getServletContext().getRealPath("/" + product.getImage());
			
			File file = new File(path);
			
			file.delete();
		}
		
		productService.delete(product);
		
		return "deleteSuccess";
	}
	
	
	/*
	 * 修改商品
	 */
	public String edit()
	{
		//第一步，先查询出要修改的商品
		product = productService.findByPid(product.getPid());
		//第二步，把所有的二级分类查询出来
		List<CategorySecond> csList = categorySecondService.findAll();
		
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "editSuccess";
	}
	
	/**
	 * 更新商品信息
	 * @return
	 * @throws IOException 
	 */
	public String update() throws IOException
	{
		System.out.println("更新商品");
		product.setPdate(new Date());

		// 上传:
		if (upload != null) {
			String delPath = ServletActionContext.getServletContext().getRealPath("/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		
		productService.update(product);
		
		return "updateSuccess";
	}
	
	

}
