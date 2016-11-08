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

import com.goodlife.dao.InstructorDAO;
import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.dao.StudentDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;
import com.goodlife.model.ObjectPair;
import com.goodlife.model.Student;
import com.goodlife.model.Users;

@Controller
@Transactional
@RequestMapping(value = "/instructor")
public class InstructorController {
	
	static final Logger logger = LogManager.getLogger(InstructorController.class.getName());
	
	@Autowired
	private InstructorDAO instructorDAO;
	@Autowired
	private StudentDAO studentDAO;
	@Autowired
	private UsersDAO usersDAO;
	@Autowired
	private ShortAnswerUserAnswerDAO shortAnswerUserAnswerDAO;
	
	@ResponseBody
	@RequestMapping(value = "/findinstructorbyuserid", method = RequestMethod.GET)
	public String findInstructorByUserId(@RequestParam(value = "userId") Integer userId){
		
		Instructor instructor;
		try {
			instructor = instructorDAO.findInstructorByUserId(userId);
		} catch (UserNotFoundException e1) {
			instructor = null;
			e1.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString((instructor == null) ? "" : instructor);
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
	@RequestMapping(value = "/getstudentprogress", method = RequestMethod.GET)
	public String getStudentProgress(@RequestParam(value = "userId") Integer userId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.getStudentProgress(userId));
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
	@RequestMapping(value = "/addinstructor", method = RequestMethod.GET)
	public String addInstructor(@RequestParam(value = "userId") Integer userId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.addInstructor(userId));
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
	@RequestMapping(value = "/addStudentToRoster", method = RequestMethod.GET)
	public String addStudentToRoster(@RequestParam(value = "userId") Integer userId,
									 @RequestParam(value = "instructorId") Integer instructorId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.addStudentToRoster(userId,instructorId));
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
	@RequestMapping(value = "/removestudentfromroster", method = RequestMethod.GET)
	public String removeStudentFromRoster(@RequestParam("userId") Integer userId,
										  @RequestParam("instructorId") Integer instructorId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.removeStudentFromRoster(userId,instructorId));
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
	@RequestMapping(value = "/changerostercapsize", method = RequestMethod.GET)
	public String changeRosterCapSize(@RequestParam("instructorId") Integer instructorId,
									  @RequestParam("rosterSize") Integer rosterSize){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.changeRosterCapSize(instructorId,rosterSize));
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
	@RequestMapping(value = "/approveshortanswer", method = RequestMethod.GET)
	public String approveUserAnswer(@RequestParam("userId") Integer userId,
									 @RequestParam("saQId") Integer saQId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnswerUserAnswerDAO.approveAnswer(userId,saQId));
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
	@RequestMapping(value = "/listallshortansbysubchap", method = RequestMethod.GET)
	public String listAllShortAnsBySubChap(@RequestParam("userId") Integer userId,
										   @RequestParam("subChapId") Integer subChapId){
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(shortAnswerUserAnswerDAO.listSubmittedShortAnsBySubChap(userId,subChapId));
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
	@RequestMapping(value = "/liststudentsbyroster", method = RequestMethod.GET)
	public String listStudentsByRoster(@RequestParam("instructorId") Integer instructorId){
		
		List<ObjectPair> studentInfoList = new ArrayList<ObjectPair>();
		List<Student> studentList = studentDAO.findStudentByInstructorId(instructorId);
		
		for(int i = 0; i < studentList.size(); i++){	
			Users user;
			try {
				user = usersDAO.findByUserId(studentList.get(i).getUserId());
			} catch (UserNotFoundException e) {
				user = new Users();
				e.printStackTrace();
			}
			studentInfoList.add(new ObjectPair(studentList.get(i),user));
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(studentInfoList);
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
	@RequestMapping(value = "/editinstructorprofile", method = RequestMethod.GET)
	public String editInstructorProfile(@RequestParam("userId") Integer userId,
										@RequestParam("aboutMe") String aboutMe,
										@RequestParam("city") String city,
										@RequestParam("email") String email,
										@RequestParam("firstname") String firstname,
										@RequestParam("lastname") String lastname,
										@RequestParam("state") String state){
		
		Boolean response = Boolean.TRUE;
		try {
			Users user = usersDAO.findByUserId(userId);
			//Instructor instructor = instructorDAO.findInstructorByUserId(userId);
			
			user.setAboutMe(aboutMe);
			user.setCity(city);
			user.setEmail(email);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setState(state);
			
			if(usersDAO.addUser(user) == null)
				response = Boolean.FALSE;
			
		} catch (UserNotFoundException e1) {
			response = Boolean.FALSE;
			e1.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp = "";
		
		try {
			jsonResp = mapper.writeValueAsString(response);
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
