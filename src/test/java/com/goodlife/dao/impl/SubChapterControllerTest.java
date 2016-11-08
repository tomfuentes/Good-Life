package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.SubChapterController;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.SubChapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class SubChapterControllerTest {

	private static final Integer CHAP_ID = 1;
	private static final Integer SUB_CHAP_ID = 1;
	private static final String SUB_CHAP_TITLE = "NEW SUB CHAPTER TITLE";
	private static final Integer ORDER = 3;
	private static final String SUB_CHAP_DESC = "NEW SUB CHAPTER DESCRIPTION";
	
	@Autowired
	private SubChapterController subChapterController;
	
	@Autowired
	private SubChapterDAO subChapterDAO;
	
	@SuppressWarnings("unused")
	@Before
	public void setUp() throws ChapterNotFoundException {
		SubChapter subChapter = createSubChapter();
	}

	@Test
	@Transactional
	public void testAddSubChapter() throws SubChapterNotFoundException {
		Integer subChapId = Integer.valueOf(subChapterController.addSubChapter(CHAP_ID,SUB_CHAP_TITLE,SUB_CHAP_DESC,ORDER));
		assertTrue(subChapId > 0);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteSubChapter() throws SubChapterNotFoundException{		
		Boolean success = Boolean.valueOf(subChapterController.deleteSubChapter(SUB_CHAP_ID));
		assertTrue(success);
	}
	
	@Test
	@Transactional
	public void testListAllSubChaptersByChapter() throws SubChapterNotFoundException{
		String subChapList = subChapterController.listAllSubChaptersByChapter(CHAP_ID);
		System.out.println(subChapList);
		assertTrue(subChapList.length() > 0);
	}
	
	@Test
	@Transactional
	public void testUpdateSubChapter() throws SubChapterNotFoundException{
		Integer newChapId = 3;
		Integer newSubChapId = 2;
		String newSubChapDescr = "New SubChapter Description";
		String newSubChapTitle = "New SubChapter Title";
		Integer newOrderId = 2;
		Boolean newPublished = true;
		
		Boolean isUpdated = Boolean.valueOf(subChapterController.updateSubChapter(newSubChapId, newChapId, newSubChapDescr, newSubChapTitle, newOrderId, newPublished));
		assertTrue(isUpdated);
	}
	
	@Test
	@Transactional
	public void testUpdateSubChapterOrder() throws SubChapterNotFoundException{		
		List<Integer> subChapList = new ArrayList<Integer>();
		subChapList.add(0, Integer.valueOf(2));
		subChapList.add(1, Integer.valueOf(1));
		Boolean success = Boolean.valueOf(subChapterController.updateSubChapterOrder(subChapList));
		assertTrue(success);
		int subChapOrder = subChapterDAO.getSubChapterById(2).getOrderId();
		assertEquals(1,subChapOrder);
	}
	
	@Test
	@Transactional
	public void testUpdateSubChapterTitle() throws SubChapterNotFoundException{		
		Boolean success = Boolean.valueOf(subChapterController.updateSubChapterTitle(SUB_CHAP_ID,SUB_CHAP_TITLE));
		assertTrue(success);
		assertEquals(SUB_CHAP_TITLE,subChapterDAO.getSubChapterById(SUB_CHAP_ID).getSubChapTitle());
	}
	
	@Test
	@Transactional
	public void testUpdateSubChapterDescr() throws SubChapterNotFoundException{		
		Boolean success = Boolean.valueOf(subChapterController.updateSubChapterDescr(SUB_CHAP_ID,SUB_CHAP_DESC));
		assertTrue(success);
		assertEquals(SUB_CHAP_DESC,subChapterDAO.getSubChapterById(SUB_CHAP_ID).getSubChapDescr());
	}
	
	public static SubChapter createSubChapter() {
		SubChapter subChapter = new SubChapter();
		subChapter.setSubChapTitle(SUB_CHAP_TITLE);
		subChapter.setSubChapDescr(SUB_CHAP_DESC);
		subChapter.setOrderId(ORDER);
		subChapter.setChapId(CHAP_ID);
		return subChapter;
	}
}
