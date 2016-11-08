package com.goodlife.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

@Repository
public class UsersDAOImpl implements UsersDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer addUser(Users user) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user.getUserId();
	}

	@Override
	public Boolean deleteUser(String username) {
		Users user;
		try {
			user = findByUserName(username);
			this.sessionFactory.getCurrentSession().delete(user);
			return Boolean.TRUE;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@Override
	public Users findByUserName(String username) throws UserNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.eqOrIsNull("username", username));
		Users user = (Users) criteria.uniqueResult();
		if (null == user) {
			throw new UserNotFoundException("User: " + username
					+ ".  Not found in the database!");
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByLastName(String lastname) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
		criteria.add(Restrictions.ilike("lastname", lastname));
		List<Users> userList = criteria.list();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByFirstName(String firstname) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
		criteria.add(Restrictions.ilike("firstname", firstname));
		List<Users> userList = criteria.list();
		return userList;
	}

	@Override
	public Users findByEmail(String email) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("email", email));
		Users user = (Users) criteria.uniqueResult();
		if(user == null)
			return null;
		else
			return user;
	}
	
	@Override
	public Users findByUserId(Integer userId) throws UserNotFoundException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("userId", userId));
		Users user = (Users) criteria.uniqueResult();
		if(user == null)
			throw new UserNotFoundException("User with userId: " + userId + " not found.");
		else
			return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByCity(String city) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.ilike("city", city));
		List<Users> userList = criteria.list();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByState(String state) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.ilike("state", state));
		List<Users> userList = criteria.list();
		return userList;
	}

	@Override
	public Boolean disableUser(String username) {
		Users user;
		try {
			user = findByUserName(username);
			user.setRegistered(Boolean.FALSE);
			this.sessionFactory.getCurrentSession().saveOrUpdate(user);
			return Boolean.TRUE;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		
	}

	@Override
	public Boolean enableUser(String username) {
		Users user;
		try {
			user = findByUserName(username);
			user.setRegistered(true);
			this.sessionFactory.getCurrentSession().saveOrUpdate(user);
			return Boolean.TRUE;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findByRoleTypes(List<Character> roles) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.in("roleTypeCode",roles));
		List<Users> userList = criteria.list();
		return userList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> advancedQuery(String input, String field,
			List<Character> roles) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Users.class);
		if(roles != null)
			criteria.add(Restrictions.and(Restrictions.sqlRestriction(field + " like '%" + input + "%'"),
					 Restrictions.in("roleTypeCode",roles)));
		else
			criteria.add(Restrictions.sqlRestriction(field + " like '%" + input + "%'"));
		return criteria.list();
		
	}

	@Override
	public Boolean promoteUser(String username, char roleTypeCode) {
		Users user;
		try {
			user = findByUserName(username);
			user.setRoleTypeCode(roleTypeCode);
			this.sessionFactory.getCurrentSession().saveOrUpdate(user);
			return Boolean.TRUE;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		
	}
}
