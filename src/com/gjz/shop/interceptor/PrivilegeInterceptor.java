package com.gjz.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.adminuser.entity.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * ��̨Ȩ��У���������
 * @author GuoJiazhen
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		
		//�ж�Session���Ƿ񱣴��˺�̨�û�����Ϣ
		
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		
		if(existAdminUser == null)
		{
			//û�е�¼���з���
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			
			actionSupport.addActionError("�ף�����û�е�¼��û��Ȩ�޷��ʣ�");
			
			return "loginFail";
		}
		else
		{
			//�Ѿ������˵�¼
			return actionInvocation.invoke();
		}
		
	}
	
	
	
}
