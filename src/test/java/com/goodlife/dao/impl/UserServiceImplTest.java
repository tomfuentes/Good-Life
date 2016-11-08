package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Student;
import com.goodlife.model.Users;
import com.goodlife.service.impl.LoginServiceImpl;
import com.goodlife.service.impl.UserServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" }) 
public class UserServiceImplTest {

	private static final Integer USER_ID = 1;
	private static final String USER_NAME = "dhaval";
	private static final Integer INV_CD = 123456;
	private static final char ROLE = 'S';
	private static final String EMAIL = "dhaval.raj@tsgforce.com";
	private static final String FNAME = "Dhaval";
	private static final String LNAME = "Raj";
	

	@Autowired
	private UserServiceImpl userService;
	
	/*@Before
	public void setUp() throws UserNotFoundException {
		Users user = createUser();
		UsersDAO usersDao = Mockito.mock(UsersDAO.class);
		when(usersDao.findByUserName(USER_NAME)).thenReturn(user);
	}*/


	@Test
	@Transactional
	public void testFindByUsername() throws UserNotFoundException {
		Users user = userService.findByUserName(USER_NAME);
		assertEquals(user.getUserId(), USER_ID);
	}
	
	public static Users createUser() {
		Users user = new Users();
		user.setUsername(USER_NAME);
		user.setInvitationCode(INV_CD);
		user.setEmail(EMAIL);
		user.setFirstname(FNAME);
		user.setLastname(LNAME);
		user.setRegistered(true);
		return user;
	}
}
