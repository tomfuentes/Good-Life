package com.goodlife.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.UserStatusDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.StateNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UserStatus;
import com.goodlife.model.Users;

/*
 * User management on (filtered) user list for Super Admin
 */
@Controller
@RequestMapping(value = "/usermanagement")
@Transactional
public class UserManagementController {
	
	static final Logger logger = LogManager.getLogger(UserManagementController.class.getName());
	
	@Inject
	private UserStatusDAO userStatusDAO;
	
	@Inject
	private UsersDAO usersDAO;
	
	/*
	 * ** field should match the db column name.
	 * example from UI : { "input": "whateveUserTypes",
	 * 						"field": "lst_nm", "frst_nm", "email", "usr_nm"
	 * 						"sb": 1,
	 * 						"mb": 0,
	 * 						"fb": 0}
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public String getList(@RequestParam(value="input") String input, 
				@RequestParam(value="field") String field,
				@RequestParam(value="sb") Integer sb,
				@RequestParam(value="mb") Integer mb,
				@RequestParam(value="fb") Integer fb) {

		List<Users> filteredList = new ArrayList<Users>();
		List<Object> userAndStatusList = new ArrayList<Object>();
		List<UserStatus> userStatusList = new ArrayList<UserStatus>();
		UserStatus userStatus;
		String searchStr = cleanInput(input, field);
		
		List<Character> roles = new ArrayList<Character>();
		if (sb == 1) {
			roles.add('S');
		} if (mb == 1) {
			roles.add('M');
		} if (fb == 1) {
			roles.add('F');
		}
	
		filteredList = usersDAO.advancedQuery(searchStr, field, roles);
		
		for(int i = 0; i < filteredList.size(); i++)
		{
			userStatus = userStatusDAO.findCurrentStatusByUser(filteredList.get(i).getUserId());
			if(userStatus == null){
				userStatus = new UserStatus();
				userStatus.setStatusTypeCode(Character.valueOf('a'));
			}
			userStatusList.add(userStatus);
		}
		
		userAndStatusList.add(filteredList);
		userAndStatusList.add(userStatusList);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(userAndStatusList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	
	/**
	 * addUserStatus - Assigns a user status
	 * @param userId
	 * @param statusTypeCode - s(suspended), d(deleted)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/adduserstatus", method = RequestMethod.POST)
	public String addUserStatus(@RequestParam(value="userId") Integer userId,
									@RequestParam(value="statusTypeCode") Character statusTypeCode) {

		UserStatus userStatus = userStatusDAO.findCurrentStatusByUser(userId);
		if(userStatus != null && Character.toUpperCase(userStatus.getStatusTypeCode()) != 'D')
			userStatusDAO.changeEndDate(userStatus.getUserStatusId(), new Date());
		else if(userStatus != null && Character.toUpperCase(userStatus.getStatusTypeCode()) == 'D')
			userStatusDAO.deleteUserStatus(userStatus.getUserStatusId());
		userStatus = new UserStatus();
		Date userStatusTS = new Date();
		userStatus.setUserId(userId);
		userStatus.setStartDate(new Timestamp(new Date().getTime()));
		// 604800000 is the amount of milliseconds in 7 days
		userStatus.setEndDate(new Timestamp(new Date().getTime() + 604800000));	
		userStatus.setStatusTypeCode(statusTypeCode);
		userStatus.setUserStatusTS(userStatusTS);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(userStatusDAO.addUserStatus(userStatus));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	
	
	/**
	 * deleteUserStatus - Undo a status
	 * @param userStatusId 
	 * @return
	 * @throws UserNotFoundException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteuserstatus", method = RequestMethod.GET)
	public String deleteUserStatus(@RequestParam(value="userStatusId") Integer userStatusId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(userStatusDAO.deleteUserStatus(userStatusId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	/**
	 * changeEndDate - Change the end date of suspension
	 * @param userStatusId
	 * @param newDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeenddate", method = RequestMethod.POST)
	public String changeEndDate(@RequestParam(value="userStatusId") Integer userStatusId, @RequestParam(value="newDate") Date newDate){
		
		Boolean response = userStatusDAO.changeEndDate(userStatusId, newDate);
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
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
	
	private String cleanInput(String input, String type) {
		
		input = input.trim();
		if (type == "state") {
			StateConversionUtil stateUtil = new StateConversionUtil();
			try {
				return stateUtil.lookUp(input);
			} catch (StateNotFoundException e) {
				logger.debug(input + " is an invalid state name.");
				e.printStackTrace();
			}
		}
		return input;
	}
}
