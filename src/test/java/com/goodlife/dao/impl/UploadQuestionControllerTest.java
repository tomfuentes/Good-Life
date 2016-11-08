package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.UploadQuestionController;
import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.model.UploadFileQ;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class UploadQuestionControllerTest {

	private static final Integer UP_Q_ID = 1;
	private static final Integer NEW_UP_Q_ID = 3;
	private static final String NEW_QUESTION = "This is a new question";
	private static final Integer SUBCHAPID = 1;
	private static final Integer NEW_SUBCHAPID = 3;
	private static final String NEW_HELP_TXT = "This is new help text.";
	
	@Autowired
	private UploadQuestionController uploadQuestionController;
	
	@Autowired
	private UploadFileQDAO uploadFileQDAO;
	
	@Test
	@Transactional
	public void testAddUploadFileQuestion() {
		String uploadId = uploadQuestionController.addUploadFileQuestion(NEW_SUBCHAPID, NEW_HELP_TXT,
																		  NEW_QUESTION);
		assertEquals(uploadId,String.valueOf(NEW_UP_Q_ID));
		assertEquals(uploadFileQDAO.getUploadFileQuestion(NEW_SUBCHAPID).getDescription(),NEW_QUESTION);
		assertEquals(uploadFileQDAO.getUploadFileQuestion(NEW_SUBCHAPID).getHelpText(),NEW_HELP_TXT);
	}
	
	@Test
	@Transactional
	public void testUpdateDescription(){
		String success = uploadQuestionController.updateDescription(UP_Q_ID, NEW_QUESTION);
		assertEquals(success.toUpperCase(),"TRUE");
		assertEquals(uploadFileQDAO.getUploadFileQuestion(UP_Q_ID).getDescription(),NEW_QUESTION);
	}
	
	@Test
	@Transactional
	public void testUpdateHelpText(){
		String success = uploadQuestionController.updateHelpText(UP_Q_ID, NEW_HELP_TXT);
		assertEquals(success.toUpperCase(),"TRUE");
		assertEquals(uploadFileQDAO.getUploadFileQuestion(UP_Q_ID).getHelpText(),NEW_HELP_TXT);
	}
	
	@Test
	@Transactional
	public void testAllUploadQuestionsBySubchapId(){
		String uploadList = uploadQuestionController.getUploadQuestionBySubchapId(SUBCHAPID);
		System.out.println(uploadList);
		assertNotNull(uploadList);
	}
	
	@Test
	@Transactional
	public void testDeleteUploadFileQuestion(){
		UploadFileQ uploadFileQ = uploadFileQDAO.getUploadFileQuestion(SUBCHAPID);
		String success = uploadQuestionController.deleteUploadFileQuestion(UP_Q_ID);
		assertTrue(success.toUpperCase().equals("TRUE"));
		assertTrue(uploadFileQ != null);
	}
}
