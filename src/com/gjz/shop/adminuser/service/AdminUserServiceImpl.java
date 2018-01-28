package com.gjz.shop.adminuser.service;

import com.gjz.shop.adminuser.dao.AdminUserDao;
import com.gjz.shop.adminuser.entity.AdminUser;

public class AdminUserServiceImpl implements AdminUserService {

	private AdminUserDao adminUserDao;
	
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	@Override
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}
	
}
