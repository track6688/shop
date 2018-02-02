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
	
	private File upload;	//�ϴ����ļ�
	private String uploadFileName;	//�ļ���
	private String uploadContentType;	//�ļ�����
	
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
	 * �������е���Ʒ
	 */
	public String findAll()
	{
		PageBean<Product> pageBean =  productService.findByPage(page);
		
		//��ֵջ�������pageBean
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	/*
	 * ��ת������ҳ��
	 */
	public String addPage()
	{
		
		List<CategorySecond> csList =  categorySecondService.findAll();
		
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "addPage";
	}
	
	/**
	 * ������Ʒ
	 * @return
	 * @throws IOException 
	 */
	public String save() throws IOException
	{
		product.setPdate(new Date());
		
		System.out.println(upload);
		
		if(upload != null)
		{
			//����·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			
			//����һ���ļ�
			File diskFile = new File(realPath + "//" + uploadFileName);
			
			//���Ϊ�ļ�
			FileUtils.copyFile(upload, diskFile);
			
			product.setImage("products/" + uploadFileName);
			
		}
		
		productService.save(product);
		
		return "saveSuccess";
	}
	
	
	/**
	 * ɾ����Ʒ
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
	 * �޸���Ʒ
	 */
	public String edit()
	{
		//��һ�����Ȳ�ѯ��Ҫ�޸ĵ���Ʒ
		product = productService.findByPid(product.getPid());
		//�ڶ����������еĶ��������ѯ����
		List<CategorySecond> csList = categorySecondService.findAll();
		
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "editSuccess";
	}
	
	/**
	 * ������Ʒ��Ϣ
	 * @return
	 * @throws IOException 
	 */
	public String update() throws IOException
	{
		System.out.println("������Ʒ");
		product.setPdate(new Date());

		// �ϴ�:
		if (upload != null) {
			String delPath = ServletActionContext.getServletContext().getRealPath("/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// ����ϴ�ͼƬ�ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath("/products");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + uploadFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		
		productService.update(product);
		
		return "updateSuccess";
	}
	
	

}
