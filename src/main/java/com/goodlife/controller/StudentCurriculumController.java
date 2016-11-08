package com.goodlife.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.MultiChoiceListDAO;
import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.dao.InstructorDAO;
import com.goodlife.dao.StudentDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.model.Chapter;
import com.goodlife.model.MultiChoiceList;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceQ;
import com.goodlife.model.Node;
import com.goodlife.model.ObjectPair;
import com.goodlife.model.ShortAnswerQ;
import com.goodlife.model.Student;
import com.goodlife.model.SubChapter;
import com.goodlife.model.Tree;
import com.goodlife.model.UploadFileQ;

@Controller
@RequestMapping(value = "/student")
@Transactional
public class StudentCurriculumController {
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
	private MultiChoiceListDAO multiChoiceListDAO;
	@Autowired
	private ShortAnswerUserAnswerDAO shortAnswerUserAnsDAO;
	@Autowired
	private ShortAnswerQDAO shortAnswerQDAO;
	@Autowired
	private UploadedAnswerDAO uploadedAnswerDAO;
	@Autowired
	private UploadFileQDAO uploadFileQDAO;
	@Autowired
	private InstructorDAO instructorDAO;
	@Autowired
	private UsersDAO usersDAO;
	
	@ResponseBody
	@RequestMapping(value = "/getallowedchapters", method = RequestMethod.GET)
	public String getAllowedChapters(@RequestParam(value = "userId") Integer userId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(studentDAO.getAllowedChapters(userId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getprogress", method = RequestMethod.GET)
	public String getProgress(@RequestParam(value = "userId") Integer userId){
		
		Student student = studentDAO.findStudentByUserId(userId);
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		Double progress = instructorDAO.getStudentProgress(userId)*100;
		
		try {
			jsonResp = mapper.writeValueAsString(progress.intValue());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
		
	}
	
	/*
	 * The JSON response is an array of ObjectPair objects(which contain a chapter object and its corresponding subchapter list)
	 * The response first prints off all the chapter objects and then the corresponding sub chapter list
	 * Example string:
	 * {"objR":{"chapId":1,"chapDescr":"CHAPTER 1 DESCRIPTION","chapTitle":"CHAPTER 1 TITLE","orderId":1,"published":true},
	 * "objL":[{"subChapId":1,"chapId":1,"subChapDescr":"Sub Chapter 1 Description","subChapTitle":"Sub Chapter 1 Title","orderId":1,"published":true}
	 */
	@ResponseBody
	@RequestMapping(value = "/getallowedcurriculum", method = RequestMethod.GET)
	public String getAllowedCurriculum(@RequestParam(value = "userId") Integer userId){
		
		List<ObjectPair> curriculumList = new ArrayList<ObjectPair>();
		List<Chapter> chapterList = studentDAO.getAllowedChapters(userId);
		List<SubChapter> subChapList;
		
		for(int i = 0; i < chapterList.size(); i++){
			subChapList = subChapDAO.getPublishedSubChapListByChap(chapterList.get(i).getChapId());
			if(subChapList == null)
				subChapList = new ArrayList<SubChapter>();
			curriculumList.add(new ObjectPair(chapterList.get(i),subChapList));
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(curriculumList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getsubchapsbychapter", method = RequestMethod.GET)
	public String getSubChapsByChapter(@RequestParam(value = "chapId") Integer chapId){
		
		List<SubChapter> subChapList = new ArrayList<SubChapter>();
		subChapList = subChapDAO.getPublishedSubChapListByChap(chapId);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(subChapList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getmultichoicebylist", method = RequestMethod.GET)
	public String getMultiChoiceBySubChap(@RequestParam(value = "multiChoiceListId") Integer multiChoiceListId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(multiChoiceQDAO.getAllPublishedMultiChoice(multiChoiceListId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getmultichoiceoptions", method = RequestMethod.GET)
	public String getMultiChoiceOptions(@RequestParam(value = "multiQuesId") Integer multiQuesId){
		
		List<MultiChoiceOption> optionList = new ArrayList<MultiChoiceOption>();
		optionList = multiChoiceOptionDAO.getPublishedMultiChoiceOptions(multiQuesId);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(optionList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}

	@ResponseBody
	@RequestMapping(value = "/getuploadquesbysubchap", method = RequestMethod.GET)
	public String getUploadFileQBySubChap(@RequestParam(value = "subChapId") Integer subChapId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadFileQDAO.getPublishedUploadFileQBySubchapId(subChapId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getmultichoiceuseranswer", method = RequestMethod.GET)
	public String getMultiChoiceUserAnswer(@RequestParam(value = "userId") Integer userId,
											@RequestParam(value = "multiQuesId") Integer multiQuesId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(multiChoiceUserAnsDAO.getUserAnswerObj(userId, multiQuesId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getshortansweruseranswer", method = RequestMethod.GET)
	public String getShortAnswerUserAnswer(@RequestParam(value = "userId") Integer userId,
														  @RequestParam(value = "shortAnsId") Integer shortAnsId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnswerUserAnsDAO.getUserAnswer(userId, shortAnsId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getuploadedans", method = RequestMethod.GET)
	public String getUploadedAnswerUserAnswer(@RequestParam(value = "userId") Integer userId,
											  @RequestParam(value = "uploadQuesId") Integer uploadQuesId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(uploadedAnswerDAO.getUserAnswer(userId, uploadQuesId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/issubchapcomplete", method = RequestMethod.GET)
	public String isSubChapComplete(@RequestParam(value = "userId") Integer userId,
									 @RequestParam(value = "subChapId") Integer subChapId){
		
		Boolean isComplete = multiChoiceUserAnsDAO.isMultiChoiceSubChapComplete(userId, subChapId) ||
							 shortAnswerUserAnsDAO.isShortAnswerSubChapComplete(userId, subChapId) ||
							 uploadedAnswerDAO.isUploadedQuestionComplete(userId, subChapId);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isComplete);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	/*
	 * Returns an array of the form object(ie upload file question)
	 * and a character representing the type of object(ie 'm')
	 * The array is always of size 2 or null.
	 * 
	 * Return types for index 1
	 * - List<Tree<Object>> --> has multiple choice list and question objects
	 * - List<ShortAnswerQ>
	 * - List<UploadFileQ> --> always size = 1
	 * 
	 * Return types for index 2
	 * - 'm' --> for list of multiple choice list
	 * - 's' --> for list of short answer questions
	 * - 'u' --> for a single upload file question
	 */
	// TODO multi choice list
	@ResponseBody
	@RequestMapping(value = "/getsubchapform", method = RequestMethod.GET)
	public String getSubChapForm(@RequestParam(value = "subChapId") Integer subChapId){
		
		ArrayList<Object> formArray = new ArrayList<Object>();
		List<MultiChoiceList> multiList = multiChoiceListDAO.getAllPublishedMultiChoiceLists(subChapId);
		List<ShortAnswerQ> shortAnsList = shortAnswerQDAO.getPublishedShortAnswerBySubChapter(subChapId);
		UploadFileQ uploadQ = uploadFileQDAO.getPublishedUploadFileQBySubchapId(subChapId);
		
		if(multiList != null && multiList.isEmpty() == false){
			
			Tree<Object> tree;
			Node<Object> mcList;
			List<MultiChoiceQ> mcQList;
			List<Tree<Object>> treeList = new ArrayList<Tree<Object>>();
			
			for(int i = 0; i < multiList.size(); i++){
				tree = new Tree<Object>();
				mcList = new Node<Object>(multiList.get(i));
				mcQList = multiChoiceQDAO.getAllPublishedMultiChoice(multiList.get(i).getMultiChoiceListId());
				for(int j = 0; j < mcQList.size(); j++)
					mcList.addChild(new Node<Object>(mcQList.get(j)));
				tree.setRoot(mcList);
				treeList.add(tree);
			}
			
			formArray.add(treeList);
			formArray.add('m');
		}
		else if(shortAnsList != null && shortAnsList.isEmpty() == false){
			formArray.add(shortAnsList);
			formArray.add('s');
		}
		else if(uploadQ != null){
			List<UploadFileQ> uploadList = new ArrayList<UploadFileQ>();
			uploadList.add(uploadQ);
			formArray.add(uploadList);
			formArray.add('u');
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(formArray);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatecurrentchapter", method = RequestMethod.GET)
	public String updateCurrentChapter(@RequestParam(value = "userId") Integer userId,
										@RequestParam(value = "chapId") Integer chapId){
		
		Boolean isSuccess;
		if(studentDAO.updateCurrentChapter(userId, chapId) == null)
			isSuccess = Boolean.FALSE;
		else
			isSuccess = studentDAO.updateCurrentChapter(userId, chapId);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(isSuccess);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
}