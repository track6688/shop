package com.gjz.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.adminuser.entity.AdminUser;
import com.gjz.shop.adminuser.service.AdminUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {

	private AdminUser adminUser = new AdminUser();
	
	private AdminUserService adminUserService;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	
	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	
	
	/**
	 * 后台登录的方法
	 */
	public String login()
	{
		
		AdminUser existAdminUser = adminUserService.login(adminUser);
		
		if(existAdminUser == null)
		{
			//登录失败
			this.addActionError("亲！您的用户名或者密码错误！");
			
			return "loginFail";
		}
		else
		{
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			
			return "loginSuccess";
			
		}
		
	}
	

}
