package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.UserManagementController;
import com.goodlife.dao.UserStatusDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class UserManagementControllerTest {

	private static final Integer USER_ID = 1;
	private static final String USER_NAME = "dhaval";
	private static final Integer INV_CD = 123456;
	private static final char ROLE = 'S';
	private static final String EMAIL = "dhaval.raj@tsgforce.com";
	private static final String FNAME = "Dhaval";
	private static final String LNAME = "Raj";
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private UserStatusDAO userStatus;
	
	@Autowired
	private UserManagementController userManagement;
	
	@SuppressWarnings("unused")
	@Before
	public void setUp() throws UserNotFoundException {
		Users user = createUser();
	}

	@Test
	@Transactional
	public void testGetList() {
		String userList = userManagement.getList("student", "lst_nm", 1, 0, 0);
		System.out.println(userList);
		assertTrue(userList.length() > 0);
	}
	
	@Test
	@Transactional
	public void testAddUserStatus() throws UserNotFoundException{
		Integer success = Integer.valueOf(userManagement.addUserStatus(USER_ID,'s'));
		List<UserStatus> userList = userStatus.findCurrentSuspendedUsers();
		for(int i = 0; i< userList.size(); i++)
			System.out.println(userList.get(i).getEndDate());
		assertTrue(userList.size() > 0);
		System.out.println(success);
		assertTrue(success > 0);
	}
	
	@Test
	@Transactional
	public void testSuspendUser() throws UserNotFoundException{
		assertTrue(Integer.valueOf(userManagement.addUserStatus(USER_ID,'s')) > 0);
		assertTrue(Integer.valueOf(userManagement.addUserStatus(USER_ID,'d')) > 0);
		List<UserStatus> status = userStatus.findByUserId(USER_ID);
		assertTrue(status.size() > 2);
		
	}
	
	@Test
	@Transactional
	public void testDeleteUserStatus() throws UserNotFoundException {
		Integer userStatusId = Integer.valueOf(userManagement.addUserStatus(USER_ID, 'd'));
		assertTrue(Boolean.valueOf(userManagement.deleteUserStatus(userStatusId)));
	}
	
	@Test
	@Transactional
	public void testChangeEndDate() throws UserNotFoundException{
		Integer userStatusId = Integer.valueOf(userManagement.addUserStatus(USER_ID, 's'));
		Date oldDate = userStatus.findByUserStatusId(userStatusId).getEndDate();
		userManagement.changeEndDate(userStatusId, new Date());
		assertTrue(oldDate != userStatus.findByUserStatusId(userStatusId).getEndDate());
	}
	
	
	public static Users createUser() {
		Users user = new Users();
		user.setUsername(USER_NAME);
		user.setInvitationCode(INV_CD);
		user.setEmail(EMAIL);
		user.setFirstname(FNAME);
		user.setLastname(LNAME);
		user.setRoleTypeCode(ROLE);
		return user;
	}
}
