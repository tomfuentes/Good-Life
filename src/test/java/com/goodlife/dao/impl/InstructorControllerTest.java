package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.InstructorController;
import com.goodlife.dao.InstructorDAO;
import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class InstructorControllerTest {
	
	private static final String USER_NAME = "tom";
	private static final String ABOUT_ME = "This is the about me section.";
	private static final String CITY = "Chicago";
	private static final String EMAIL = "tom@tsgforce.com";
	private static final String FIRST_NAME = "Tom";
	private static final String LAST_NAME = "Fuentes";
	private static final String STATE = "IL";
	private static final Integer INSTRUCTOR_USER_ID = 2;
	private static final Integer USER_ID = 14;
	private static final Integer ROSTER_ID = 4;
	private static final Integer N_STDNT = 1;
	private static final Integer TOT_CAP = 2;
	private static final Integer SA_Q_ID = 1;
	private static final Integer SUBCHAP_ID = 5;
	
	@Autowired
	private InstructorDAO instructorDAO;
	@Autowired
	private UsersDAO usersDAO;
	@Autowired
	private InstructorController instructorController;
	
	@Autowired
	private MultiChoiceUserAnsDAO multiAnsDAO;
	
	@Autowired
	private ShortAnswerUserAnswerDAO shortAnsUADAO;
	
	@Autowired
	private UploadedAnswerDAO uploadAnsDAO;
	
	@Test
	@Transactional
	public void testProgress(){
		Integer userId = 14;
		for(int subChapId = 1; subChapId < 10; subChapId++){
		System.out.println(subChapId);
		System.out.println(multiAnsDAO.isMultiChoiceSubChapComplete(userId,subChapId));
		System.out.println(shortAnsUADAO.isShortAnswerSubChapComplete(userId, subChapId));
		System.out.println(uploadAnsDAO.isUploadedQuestionComplete(userId, subChapId));
		}
		assertTrue(userId == 14);
	}
	
	@Test
	@Transactional
	public void testGetStudentProgress() {
		String studentProgress = instructorController.getStudentProgress(USER_ID);
		System.out.println(studentProgress);
		assertTrue(Double.valueOf(studentProgress) > 0);
	}
	
	@Test
	@Transactional
	public void testFindInstructorByUserName(){
		String instructor = instructorController.findInstructorByUserId(USER_ID+1);
		System.out.println(instructor);
		assertTrue(instructor.length() > 2);
	}
	
	@Test
	@Transactional
	public void testFindInstructorByInstructorId(){
		String instructor = instructorController.findInstructorByUserId(USER_ID);
		assertTrue(instructor.length() > 2);
		assertTrue(instructor.contains(String.valueOf(USER_ID)));
	}
	
	@Test
	@Transactional
	public void testAddStudentToRoster(){
		Boolean isSuccess = Boolean.valueOf(instructorController.addStudentToRoster(USER_ID + 2, USER_ID));
		assertTrue(isSuccess);
		
		
	}
	
	@Test
	@Transactional
	public void testApproveUserAnswer(){
		Boolean isApproved = Boolean.valueOf(instructorController.approveUserAnswer(USER_ID, SA_Q_ID));
		assertTrue(isApproved);
		
	}
	
	@Test
	@Transactional
	public void testListAllShortAnsBySubChap(){
		Integer shortAnsCount = instructorController.listAllShortAnsBySubChap(USER_ID, SUBCHAP_ID).length();
		assertTrue(shortAnsCount > 0);
	}
	
	@Test
	@Transactional
	public void testListStudentsByRoster(){
		String roster = instructorController.listStudentsByRoster(ROSTER_ID);
		System.out.println(roster);
		assertTrue(roster.length() > 0);
	}
	
	@Test
	@Transactional
	public void testEditInstructorProfile(){
		String instructor;
		String isSuccess = String.valueOf(Boolean.FALSE);
		ObjectMapper mapper = new ObjectMapper();
		try {
			instructor = mapper.writeValueAsString(usersDAO.findByUserId(INSTRUCTOR_USER_ID));
			System.out.println(instructor);
			isSuccess = instructorController.editInstructorProfile(INSTRUCTOR_USER_ID, 
					ABOUT_ME, CITY, EMAIL, FIRST_NAME, LAST_NAME, STATE);
			instructor = mapper.writeValueAsString(usersDAO.findByUserId(INSTRUCTOR_USER_ID));
			System.out.println(instructor);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertTrue(Boolean.valueOf(isSuccess));
	}
}
