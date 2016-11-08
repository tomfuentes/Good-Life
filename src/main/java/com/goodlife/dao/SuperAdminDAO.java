package com.goodlife.dao;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.SuperAdmin;

public interface SuperAdminDAO {
	public SuperAdmin findSuperAdminByUserName(String username) throws UserNotFoundException;
}