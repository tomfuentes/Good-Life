package com.goodlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UserRoleDAO;
import com.goodlife.model.UserRole;

@Repository
public class UsersRoleDAOImpl implements UserRoleDAO  {

	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public UserRole findByUserName(String username) {
		UserRole userRole = (UserRole) this.sessionFactory.getCurrentSession().get(UserRole.class, username);
		return userRole;
	}

	@Override
	public void addUserRole(UserRole userRole) {
		this.sessionFactory.getCurrentSession().save(userRole);
	}

	@Override
	public void deleteUserRole(String username) {
		UserRole userRole = (UserRole) this.sessionFactory.getCurrentSession().get(UserRole.class, username);
        if (null != userRole) {
        	this.sessionFactory.getCurrentSession().delete(userRole);
        }
	}
}
