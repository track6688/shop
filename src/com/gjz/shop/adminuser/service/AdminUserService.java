package com.gjz.shop.adminuser.service;

import com.gjz.shop.adminuser.dao.AdminUserDao;
import com.gjz.shop.adminuser.entity.AdminUser;

public interface AdminUserService {

	AdminUser login(AdminUser adminUser);
	
}
