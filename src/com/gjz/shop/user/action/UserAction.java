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
	
	//使用UserService
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
	
	//通过ajax检查用户名是否已经存在
	public String findByName() throws IOException
	{
		System.out.println("查找用户名》》》》》" + user);
		//调用Service
		User existUser = userService.findByUsername(user.getUsername());
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		if(existUser != null)
		{
			//用户已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}
		else
		{
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
			//没有查询到用户，用户名可以使用
		}
		
		return NONE;
	}

	/**
	 * 用户注册的方法
	 * @return
	 */
	public String register()
	{
		//判断验证码
		String checkcode1 = (String) ServletActionContext.getRequest().getSession()
			.getAttribute("checkcode");
		
		if(!checkcode.equalsIgnoreCase(checkcode1))
		{
			this.addActionError("验证码输入错误！");
			return "checkcodeFail";
		}
		
		userService.save(user);
		
		addActionMessage("注册成功，请前往邮箱查收邮件，进行激活！");
		
		return "msg";
	}
	
	
	/**
	 * 用户激活的方法
	 * @return
	 */
	public String active()
	{
		//通过激活码查找用户，如果找得到，激活成功，如果找不到激活失败
		User existUser = userService.findByCode(user.getCode());
		
		if(existUser == null)
		{
			this.addActionMessage("激活失败：激活码错误！");
		}
		else
		{
			existUser.setState(1);
			existUser.setCode(null);
			this.addActionMessage("激活成功:请去登录！");
			
			userService.update(existUser);
		}
		
		return "msg";
	}
	
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	public String loginPage()
	{
		return "loginPage";
	}
	
	/**
	 * 登录方法
	 */
	
	public String login()
	{
		
		User existUser = userService.login(user);
		
		if(existUser == null)
		{
			this.addActionError("登录失败，用户名或密码错误！");
			return LOGIN;
		}
		else
		{
			//登录成功
			//将用户的信息存入到session中
			ServletActionContext.getRequest().getSession()
				.setAttribute("existUser", existUser);
			
			return "loginSuccess";
		}
		
	}
	
	/**
	 * 退出
	 */
	public String logout()
	{
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}

}
