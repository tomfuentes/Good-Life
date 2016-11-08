package com.goodlife.dao;

import com.goodlife.model.UserRole;

public interface UserRoleDAO 
{
	public UserRole findByUserName(String username);
	void addUserRole(UserRole userRole);
	void deleteUserRole(String username);
}