package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.ChapterController;
import com.goodlife.controller.StudentAnswerController;
import com.goodlife.dao.ChapterPageDAO;
import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.ChapterPageNotFoundException;
import com.goodlife.exceptions.UploadPathException;
import com.goodlife.model.Chapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class StudentAnswerControllerTest {

	private static final Integer USER_ID = 1;
	private static final Integer UPLOAD_QUES_ID = 15;
	private static final String CHAP_TITLE = "CHAPTER 1 TITLE";
	private static final Integer ORDER = 1;
	private static final String CHAP_DESC = "CHAPTER 1 DESCRIPTION";
	private static final Boolean PUBLISHED = true;
	//private static final Integer PAGE_NUM = 1;
	private static final Integer NEW_PAGE_NUM = 3;
	private static final String FILE_PATH = "/WebContent/resources";
	private static final Integer PAGE_ID = 1;
	private static final Integer NEW_PAGE_ID = 4;
	private static final Integer MEDIA_TYPE_ID = 1;
	
	@Autowired
	private StudentAnswerController studentAnswerController;
	
	@Autowired
	private UploadedAnswerDAO uploadedAnswerDAO;
	
	@SuppressWarnings("unused")
	@Before
	public void setUp() {
	//	Chapter chapter = createChapter();
	}
	
	@Test
	@Transactional
	public void testUpdateUploadedUserAnswer() throws NumberFormatException, UploadPathException, IOException {
		
		HttpSession session = new MockHttpSession();	
		MultipartFile mpfile = new MockMultipartFile("upload.txt", "upload.txt", "text", "myContent".getBytes());
		Integer uploadAnsId = Integer.valueOf(
				studentAnswerController.updateUploadedUserAnswer(USER_ID, UPLOAD_QUES_ID, MEDIA_TYPE_ID, mpfile, session));
		assertNotNull(uploadAnsId);
		assertEquals(uploadedAnswerDAO.getUserAnswer(USER_ID, UPLOAD_QUES_ID).getUserId(), USER_ID);
		assertEquals(FILE_PATH, uploadedAnswerDAO.getUserAnswer(USER_ID, UPLOAD_QUES_ID).getFilePath());
		assertEquals(MEDIA_TYPE_ID, uploadedAnswerDAO.getUserAnswer(USER_ID, UPLOAD_QUES_ID).getMediaTypeId());
	}
	
	/*public static Chapter createChapter() {
		Chapter chapter = new Chapter();
		chapter.setChapTitle(CHAP_TITLE);
		chapter.setChapDescr(CHAP_DESC);
		chapter.setOrderId(ORDER);
		chapter.setPublished(PUBLISHED);
		chapter.setChapId(CHAP_ID);
		return chapter;
	}*/
}
