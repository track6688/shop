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
		
		
		return NONE;
	}
	

}
