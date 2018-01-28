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
	 * ��̨��¼�ķ���
	 */
	public String login()
	{
		
		AdminUser existAdminUser = adminUserService.login(adminUser);
		
		if(existAdminUser == null)
		{
			//��¼ʧ��
			this.addActionError("�ף������û��������������");
			
			return "loginFail";
		}
		else
		{
			//��¼�ɹ�
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			
			return "loginSuccess";
			
		}
		
	}
	

}
