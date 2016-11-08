package com.goodlife.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.dao.StudentDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.exceptions.MultipleChoiceOptionNotFoundException;
import com.goodlife.exceptions.ShortAnswerNotFoundException;
import com.goodlife.exceptions.UploadPathException;
import com.goodlife.model.MediaType;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceUserAns;
import com.goodlife.model.ShortAnswerQ;
import com.goodlife.model.ShortAnswerUserAnswer;
import com.goodlife.model.UploadedAnswer;

@Controller
@RequestMapping(value = "/student")
@Transactional
public class StudentAnswerController {
	
	static final Logger logger = LogManager.getLogger(StudentCurriculumController.class.getName());

	@Autowired
	private StudentDAO studentDAO;
	@Autowired
	private ChapterDAO chapterDAO;
	@Autowired
	private SubChapterDAO subChapDAO;
	@Autowired
	private MultiChoiceQDAO multiChoiceQDAO;
	@Autowired
	private MultiChoiceOptionDAO multiChoiceOptionDAO;
	@Autowired
	private MultiChoiceUserAnsDAO multiChoiceUserAnsDAO;
	@Autowired
	private ShortAnswerUserAnswerDAO shortAnswerUserAnsDAO;
	@Autowired
	private ShortAnswerQDAO shortAnswerQDAO;
	@Autowired
	private UploadedAnswerDAO uploadedAnswerDAO;
	@Autowired
	private UploadFileQDAO uploadFileQDAO;
	
	private static final String UPLOAD_DIR = "/WebContent/resources";
	
	/*@RequestMapping(value = "/updatecurrentchapter", method = RequestMethod.GET)
	public String updateStudentChapter(@RequestParam(value = "userId") Integer userId,
										@RequestParam(value = "chapId") Integer chapId){
		Boolean isCurrChapAdded = studentDAO.updateCurrentChapter(userId, chapId);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isCurrChapAdded);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResp;
	}*/
	
	
	@ResponseBody
	@RequestMapping(value = "/updateshortanswer", method = RequestMethod.GET)
	public String updateShortAnswerUserAnswer(@RequestParam(value = "userId") Integer userId,
											  @RequestParam(value = "saQId") Integer saQId,
											  @RequestParam(value = "userAnswer") String userAnswer,
											  @RequestParam(value = "submitted") String submitted){
		
		ShortAnswerUserAnswer shortAnswerUserAnswer = shortAnswerUserAnsDAO.getUserAnswer(userId, saQId);
		try {
			ShortAnswerQ shortAnswerQ = shortAnswerQDAO.getShortAnswerById(saQId);
		
			if(shortAnswerUserAnswer == null){
				shortAnswerUserAnswer = new ShortAnswerUserAnswer();
				Date shortAnsATS = new Date();
				shortAnswerUserAnswer.setSubChapId(shortAnswerQ.getSubChapId());
				shortAnswerUserAnswer.setUserId(userId);
				shortAnswerUserAnswer.setSaQId(saQId);
				shortAnswerUserAnswer.setUserAnswer(userAnswer);
				shortAnswerUserAnswer.setSubmitted(Boolean.valueOf(submitted));
				shortAnswerUserAnswer.setAprvd(false);
				shortAnswerUserAnswer.setShortAnsATS(shortAnsATS);
				
			}
			else{
				shortAnswerUserAnswer.setUserAnswer(userAnswer);
				shortAnswerUserAnswer.setSubmitted(Boolean.valueOf(submitted));
			}
		} catch (ShortAnswerNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Boolean isShortAnsUpdated = shortAnswerUserAnsDAO.addUserAnswer(shortAnswerUserAnswer);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isShortAnsUpdated);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatemultichoice", method = RequestMethod.GET)
	public String updateMultiChoiceUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "multiQuesId") Integer multiQuesId,
											@RequestParam(value = "userAnswer") Integer userAnswer){
		
		MultiChoiceUserAns multiChoiceUserAns = multiChoiceUserAnsDAO.getUserAnswerObj(userId, multiQuesId);
		Boolean isMultiChoiceAnsUpdated;
		MultiChoiceOption mcOpt;
		
		try {
			mcOpt = multiChoiceOptionDAO.findMultiChoiceOptionById(userAnswer);
		} catch (MultipleChoiceOptionNotFoundException e1) {
			mcOpt = null;
			e1.printStackTrace();
		}
			
		if(mcOpt == null)
			isMultiChoiceAnsUpdated = false;
		else if(multiChoiceUserAns == null){
			Date multiChoiceAnsTS = new Date();
			multiChoiceUserAns = new MultiChoiceUserAns(userId, multiQuesId, userAnswer, multiChoiceAnsTS);
			isMultiChoiceAnsUpdated = multiChoiceUserAnsDAO.addMultiChoiceAnswer(multiChoiceUserAns);
		}
		else{
			multiChoiceUserAns.setUserAnswer(userAnswer);
			isMultiChoiceAnsUpdated = multiChoiceUserAnsDAO.addMultiChoiceAnswer(multiChoiceUserAns);
		}
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isMultiChoiceAnsUpdated);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResp;
	}

	/*
	 * { userId : Integer
	 * uploadQuesId : the question student is answering
	 * mediaTypeId : Integer, refer to MediaType.java enum for the mapping.
	 * file : MultipartFile, the file to upload }
	 */
	@RequestMapping(value = "/updateuploadeduseranswer", method = RequestMethod.POST)
	public String updateUploadedUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "uploadQuesId") Integer uploadQuesId,
											@RequestParam(value = "mediaTypeId") Integer mediaTypeId,
											@RequestParam(value = "file") MultipartFile mpfile,
											HttpSession session) throws UploadPathException, IOException {
		
		System.out.println("Got to update upload");
		
		UploadedAnswer uploadedAnswer = uploadedAnswerDAO.getUserAnswer(userId, uploadQuesId);
		
		if(uploadedAnswer == null){
			uploadedAnswer = new UploadedAnswer();
			Date uploadAnsTS = new Date();
			uploadedAnswer.setUserId(userId);
			uploadedAnswer.setMediaTypeId(mediaTypeId);
			uploadedAnswer.setUploadQuesId(uploadQuesId);
			uploadedAnswer.setUploadAnsTS(uploadAnsTS);
			uploadStudentAnswer(uploadedAnswer, mpfile, session);

			System.out.println("Got to upload student answer");
		}
		else{
			//TODO delete file in old filepath.
			String oldFilePath = uploadedAnswer.getFilePath();
			File oldFile = new File(oldFilePath);
			System.out.println("OLD FILE PATH : " + oldFile.getAbsolutePath());
			
			uploadedAnswer.setMediaTypeId(mediaTypeId);
			uploadStudentAnswer(uploadedAnswer, mpfile, session);	
			System.out.println("Got to else case");
		}
		
		//TODO What if file was unable to save?
		Integer uploadedAnswerId = uploadedAnswerDAO.addUploadedAnswer(uploadedAnswer);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadedAnswerId);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	// helper method for updateUploadedUserAnswer upload
	private void uploadStudentAnswer(UploadedAnswer uploadedAnswer, MultipartFile mpfile, HttpSession session) throws UploadPathException, IOException {
		
		if (mpfile != null && mpfile.getSize() > 0) {
			Boolean uploadSuccess = false;
			String fileName = mpfile.getOriginalFilename();
			 
			// Create upload directory 
			String uploadDirPath = session.getServletContext().getRealPath(UPLOAD_DIR);
			if (uploadDirPath == null) {
				uploadDirPath = UPLOAD_DIR;
			}

			// different media types are stored in different directories.
			uploadDirPath += findDir(mpfile, uploadedAnswer);
			
			//Creating path for directory if it doesn't exist
			File uploadDir = new File(uploadDirPath);
			boolean directoryMade = false;
			if(!uploadDir.exists()) {
				directoryMade = uploadDir.mkdirs();
			}
			System.out.println("dir is made: "+directoryMade);
			System.out.println("directory: "+uploadDir.getPath());
			
			//Create Path for file
			String uploadFilePath = uploadDir.getPath()+"/"+fileName;
			
			FileOutputStream fos = null;
			try {
				byte[] bytes = mpfile.getBytes();
				File fileInfo = new File(uploadFilePath);
				fos = new FileOutputStream(fileInfo);
				fos.write(bytes);
				uploadSuccess = true;
			} catch(IOException e) {
				logger.error("File Save Problem", e);
			}
			
			if (uploadSuccess) {
				// trimmed filepath
				String url = trimUrl(uploadFilePath);
				System.out.println("Trimmed "+ url);
				uploadedAnswer.setFilePath(url);
				System.out.println("File Path of Uploaded File: "+uploadFilePath);
			} else {
				System.out.println("Upload success failed");
			}
		}
	}
	
	/**
	 * Creates a directory based on the file type. Sets mediaTypeId for uploadedAnswer.
	 * @param file
	 * @param uploadedAnswer
	 * @return
	 */
	private String findDir(MultipartFile file, UploadedAnswer uploadedAnswer) {
		String dir = null;

		Integer mediaType = null;
		if (file.getContentType().startsWith("image")) {
			dir = "/img";
			mediaType = MediaType.IMAGE.getMediaType();		
		} if (file.getContentType().startsWith("text")) {
			dir = "/text";
			mediaType = MediaType.TEXT.getMediaType();
		} if (file.getContentType().startsWith("video")) {
			dir = "/video";
			mediaType = MediaType.VIDEO.getMediaType();
		} if (file.getContentType().startsWith("audio")) {
			dir = "/audio";
			mediaType = MediaType.AUDIO.getMediaType();
		} if (file.getContentType().startsWith("application")) {
			dir = "/pdf";
			mediaType = MediaType.PDF.getMediaType();
		}
		
		uploadedAnswer.setMediaTypeId(mediaType);
		return dir;
	}
	
	/*
	 * returns the filename and it's current directory (without parent directory) of the real path.
	 * e.g. img/
	 */
	private String trimUrl(String s) {
		
		int j = s.lastIndexOf("/");
		String file = s.substring(j);
		s = s.substring(0,j);
		int k = s.lastIndexOf("/");
		String dir = s.substring(k+1);
		
		return dir+file;
	}
}

