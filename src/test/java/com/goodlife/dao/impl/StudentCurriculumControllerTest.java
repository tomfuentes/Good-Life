package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.StudentCurriculumController;
import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.dao.UploadedAnswerDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class StudentCurriculumControllerTest {

	private static final Integer CHAP_ID = 1;
	private static final Integer NEW_SA_Q_ID = 3;
	private static final Integer MULTI_QUES_ID = 1;
	private static final Integer SUBCHAPID = 5;
	private static final Integer USER_ID = 1;
	private static final Integer UPLOAD_Q_ID = 1;
	
	@Autowired
	private StudentCurriculumController studentCurriculumController;
	
	@Autowired
	private ShortAnswerQDAO shortAnswerQDAO;
	
	@Autowired
	private ShortAnswerUserAnswerDAO shortAnsUADAO;
	
	@Autowired
	private MultiChoiceUserAnsDAO multiChoiceUserAnsDAO;
	
	@Autowired
	private ShortAnswerUserAnswerDAO shortAnswerUserAnsDAO;

	@Autowired
	private UploadedAnswerDAO uploadedAnswerDAO;
	
	@Test
	@Transactional
	public void testGetAllowedChapters(){
		String chapterList = studentCurriculumController.getAllowedChapters(USER_ID);
		//System.out.println(chapterList);
		assertTrue(chapterList.length() > 0);
	}
	
	@Test
	@Transactional
	public void testGetSubChapsByChapter(){
		String subChapList = studentCurriculumController.getSubChapsByChapter(CHAP_ID);
		//System.out.println(subChapList);
		assertTrue(subChapList.length() > 0);
	}
	
	@Test
	@Transactional
	public void testGetMultiChoiceBySubChap(){
		String optionList = studentCurriculumController.getMultiChoiceOptions(MULTI_QUES_ID);
		//System.out.println(optionList);
		assertTrue(optionList.length() > 0);
	}
	
	@Test
	@Transactional
	public void testGetUploadFileQBySubChap(){
		String uploadQList = studentCurriculumController.getUploadFileQBySubChap(SUBCHAPID);
		//System.out.println(optionList);
		assertTrue(uploadQList.length() > 0);
	}
	
	@Test
	@Transactional
	public void testGetMultiChoiceUserAnswer(){
		String userAns = studentCurriculumController.getMultiChoiceUserAnswer(USER_ID, MULTI_QUES_ID);
		assertTrue(userAns.length() > 0);
		userAns = studentCurriculumController.getMultiChoiceUserAnswer(USER_ID, MULTI_QUES_ID+3);
		assertEquals(userAns,"null");
	}
	
	@Test
	@Transactional
	public void testGetShortAnswerUserAnswer(){
		String userAns = studentCurriculumController.getShortAnswerUserAnswer(USER_ID, NEW_SA_Q_ID-1);
		assertTrue(userAns.length()>0);
		userAns = studentCurriculumController.getShortAnswerUserAnswer(USER_ID, NEW_SA_Q_ID);
		assertEquals(userAns,"null");
	}
	
	@Test
	@Transactional
	public void testGetUploadedAnswerUserAnswer(){
		String userAns = studentCurriculumController.getUploadedAnswerUserAnswer(USER_ID, UPLOAD_Q_ID);
		assertTrue(userAns.length()>0);
		userAns = studentCurriculumController.getUploadedAnswerUserAnswer(USER_ID, UPLOAD_Q_ID+2);
		assertEquals(userAns,"null");
	}
	
	@Test
	@Transactional
	public void testIsSubChapComplete(){
		Boolean isComplete = Boolean.valueOf(studentCurriculumController.isSubChapComplete(USER_ID, SUBCHAPID));
		assertEquals(isComplete,Boolean.TRUE);
		isComplete = Boolean.valueOf(studentCurriculumController.isSubChapComplete(USER_ID, SUBCHAPID-1));
		assertEquals(isComplete,Boolean.TRUE);
		isComplete = Boolean.valueOf(studentCurriculumController.isSubChapComplete(USER_ID, SUBCHAPID-2));
		assertEquals(isComplete,Boolean.TRUE);
		isComplete = Boolean.valueOf(studentCurriculumController.isSubChapComplete(USER_ID, SUBCHAPID-3));
		assertEquals(isComplete,Boolean.TRUE);
		isComplete = Boolean.valueOf(studentCurriculumController.isSubChapComplete(USER_ID, SUBCHAPID-4));
		assertEquals(isComplete,Boolean.TRUE);
	}
	
	@Test
	@Transactional
	public void testGetForm(){
		String form;
		for(int i = 1; i <= 5; i++){
			form = studentCurriculumController.getSubChapForm(i);
			System.out.println(form);
			assertTrue(form.length() > 0);
		}
	}
		
}
