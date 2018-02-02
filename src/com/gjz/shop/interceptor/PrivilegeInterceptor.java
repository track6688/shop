package com.gjz.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.adminuser.entity.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 后台权限校验的拦截器
 * @author GuoJiazhen
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		
		//判断Session中是否保存了后台用户的信息
		
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		
		if(existAdminUser == null)
		{
			//没有登录进行访问
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			
			actionSupport.addActionError("亲！您还没有登录！没有权限访问！");
			
			return "loginFail";
		}
		else
		{
			//已经进行了登录
			return actionInvocation.invoke();
		}
		
	}
	
	
	
}
