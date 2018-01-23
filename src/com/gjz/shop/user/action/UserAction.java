package com.gjz.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.gjz.shop.user.entity.User;
import com.gjz.shop.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private User user = new User();
	
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	//ʹ��UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	public String registerPage()
	{
		return "registerPage";
	}
	
	//ͨ��ajax����û����Ƿ��Ѿ�����
	public String findByName() throws IOException
	{
		System.out.println("�����û�������������" + user);
		//����Service
		User existUser = userService.findByUsername(user.getUsername());
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		if(existUser != null)
		{
			//�û��Ѿ�����
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		}
		else
		{
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
			//û�в�ѯ���û����û�������ʹ��
		}
		
		return NONE;
	}

	/**
	 * �û�ע��ķ���
	 * @return
	 */
	public String register()
	{
		//�ж���֤��
		String checkcode1 = (String) ServletActionContext.getRequest().getSession()
			.getAttribute("checkcode");
		
		if(!checkcode.equalsIgnoreCase(checkcode1))
		{
			this.addActionError("��֤���������");
			return "checkcodeFail";
		}
		
		userService.save(user);
		
		addActionMessage("ע��ɹ�����ǰ����������ʼ������м��");
		
		return "msg";
	}
	
	
	/**
	 * �û�����ķ���
	 * @return
	 */
	public String active()
	{
		//ͨ������������û�������ҵõ�������ɹ�������Ҳ�������ʧ��
		User existUser = userService.findByCode(user.getCode());
		
		if(existUser == null)
		{
			this.addActionMessage("����ʧ�ܣ����������");
		}
		else
		{
			existUser.setState(1);
			existUser.setCode(null);
			this.addActionMessage("����ɹ�:��ȥ��¼��");
			
			userService.update(existUser);
		}
		
		return "msg";
	}
	
	
	/**
	 * ��ת����¼ҳ��
	 * @return
	 */
	public String loginPage()
	{
		return "loginPage";
	}
	
	/**
	 * ��¼����
	 */
	
	public String login()
	{
		
		User existUser = userService.login(user);
		
		if(existUser == null)
		{
			this.addActionError("��¼ʧ�ܣ��û������������");
			return LOGIN;
		}
		else
		{
			//��¼�ɹ�
			//���û�����Ϣ���뵽session��
			ServletActionContext.getRequest().getSession()
				.setAttribute("existUser", existUser);
			
			return "loginSuccess";
		}
		
	}
	
	/**
	 * �˳�
	 */
	public String logout()
	{
		//����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}

}
