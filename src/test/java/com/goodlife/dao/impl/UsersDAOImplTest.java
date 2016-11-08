package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" }) 
public class UsersDAOImplTest {

	private static final Integer USER_ID = 1;
	private static final String USER_NAME = "dhaval";
	private static final Integer INV_CD = 123456;
	private static final char ROLE = 'S';
	private static final String EMAIL = "dhaval.raj@tsgforce.com";
	private static final String FNAME = "Dhaval";
	private static final String LNAME = "Raj";
	
	@Autowired
	private UsersDAO usersDAO;

	@SuppressWarnings("unused")
	@Test(expected = com.goodlife.exceptions.UserNotFoundException.class)
	public void testDeleteUser() throws UserNotFoundException {
		usersDAO.deleteUser(USER_NAME);	
		Users found = usersDAO.findByUserName(USER_NAME);
	}

	@Test
	public void testFindByUserName() throws UserNotFoundException {
		Users found = usersDAO.findByUserName(USER_NAME);
		assertEquals(found.getUserId(), USER_ID);
	}

	@Test
	public void testDisableUser() throws UserNotFoundException {
		usersDAO.disableUser(USER_NAME);
		Users found = usersDAO.findByUserName(USER_NAME);
		assertEquals(found.isRegistered(), Boolean.FALSE);
	}

	@Test
	public void testEnableUser() throws UserNotFoundException {
		usersDAO.enableUser(USER_NAME);
		Users found = usersDAO.findByUserName(USER_NAME);
		assertEquals(found.isRegistered(), Boolean.TRUE);
	}

	@Test
	public void testFindByRoleType() throws UserNotFoundException {
		List<Character> roles = new ArrayList<Character>();
		roles.add(ROLE);
		List<Users> moderators = usersDAO.findByRoleTypes(roles);
		assertTrue(moderators.size() > 0);
	}

	@Test
	public void testFindByFirstName() throws UserNotFoundException {
		List<Users> users = usersDAO.findByFirstName(FNAME);
		assertTrue(users.size() > 0);
	}

	@Test
	public void testFindByLastName() throws UserNotFoundException {
		List<Users> users = usersDAO.findByLastName(LNAME);
		System.out.println("SIZE:" + users.size());
		assertTrue(users.size() > 0);
	}

	@Test
	public void testFindByEmail() throws UserNotFoundException {
		Users users = usersDAO.findByEmail(EMAIL);
		assertEquals(users.getUserId(), USER_ID);;
	}
	
	public static Users createUser() {
		Users user = new Users();
		user.setUsername(USER_NAME);
		user.setInvitationCode(INV_CD);
		user.setEmail(EMAIL);
		user.setFirstname(FNAME);
		user.setLastname(LNAME);
		return user;
	}
}
