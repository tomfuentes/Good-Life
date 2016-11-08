package com.goodlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.SuperAdminDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.SuperAdmin;

@Repository
public class SuperAdminDAOImpl implements SuperAdminDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public SuperAdmin findSuperAdminByUserName(String username) throws UserNotFoundException {
		SuperAdmin user = (SuperAdmin) this.sessionFactory.getCurrentSession().get(SuperAdmin.class, username);
		if (user == null) {
			throw new UserNotFoundException("User: " + username + ".  Not found in the database!");
		}
		return user;
	}
}