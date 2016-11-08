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

import com.goodlife.controller.ShortAnswerQuestionController;
import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.exceptions.ShortAnswerNotFoundException;
import com.goodlife.exceptions.SubChapterNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class ShortAnswerQuestionControllerTest {

	private static final Integer SA_Q_ID = 2;
	private static final Integer NEW_SA_Q_ID = 3;
	private static final String NEW_QUESTION = "This is a new question";
	private static final Integer SUBCHAPID = 5;
	private static final String NEW_HELP_TXT = "This is new help text.";
	private static final Integer NEW_ORDER_ID = 3;
	
	@Autowired
	private ShortAnswerQuestionController shortAnswerQuestionController;
	
	@Autowired
	private ShortAnswerQDAO shortAnswerQDAO;
	
	@Test
	@Transactional
	public void testAddShortAnswerQuestion() {
		Integer newId = Integer.valueOf(shortAnswerQuestionController.addShortAnswerQuestion(SUBCHAPID, NEW_HELP_TXT, NEW_QUESTION, NEW_ORDER_ID));
		assertEquals(newId, NEW_SA_Q_ID);
	}
	
	@Test
	@Transactional
	public void testUpdateQuestionText() throws ShortAnswerNotFoundException{
		Boolean success = Boolean.valueOf(shortAnswerQuestionController.updateQuestionText(SA_Q_ID, NEW_QUESTION));
		assertTrue(success);
		assertEquals(shortAnswerQDAO.getShortAnswerById(SA_Q_ID).getQuestion(),NEW_QUESTION);
	}
	
	@Test
	@Transactional
	public void testUpdateHelpText() throws ShortAnswerNotFoundException{
		Boolean success = Boolean.valueOf(shortAnswerQuestionController.updateHelpText(SA_Q_ID, NEW_HELP_TXT));
		assertTrue(success);
		assertEquals(shortAnswerQDAO.getShortAnswerById(SA_Q_ID).getHelpText(),NEW_HELP_TXT);
	}
	
	@Test
	@Transactional
	public void testAllQuestionsBySubChapter() throws SubChapterNotFoundException{
		String questionList = shortAnswerQuestionController.allShortAnswerQuestionsBySubchapId(SUBCHAPID);
		System.out.println(questionList);
		assertTrue(questionList.length() > 0);
	}
	
	@Test
	@Transactional
	public void testUpdateOrder() throws ShortAnswerNotFoundException{
		List<Integer> shortAnsQIdList = new ArrayList<Integer>();
		shortAnsQIdList.add(0,2);
		shortAnsQIdList.add(1,1);
		Boolean success = Boolean.valueOf(shortAnswerQuestionController.updateOrder(shortAnsQIdList));
		assertTrue(success);
		assertEquals(shortAnswerQDAO.getShortAnswerById(SA_Q_ID).getOrderId(),Integer.valueOf(1));
	}
		
}
