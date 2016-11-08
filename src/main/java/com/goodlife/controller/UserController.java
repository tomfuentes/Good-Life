package com.goodlife.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;
import com.goodlife.service.InvitationService;
import com.goodlife.service.UserService;

@Controller
public class UserController {
	static final Logger logger = LogManager.getLogger(UserController.class.getName());

	@Autowired
	InvitationService invitationService;
	
	@Autowired
	UsersDAO usersDAO;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "requestInvitationCode", method = RequestMethod.GET)
	public String sendNewInvitationCode(ModelMap model, Principal principal) {
		return "requestInviteCode.jsp";
	}

	@RequestMapping(value = "resendInvitationCode", method = RequestMethod.POST)
	public String addUserAndInvite(@ModelAttribute(value = "email") String email,
			BindingResult result, ModelMap model) {
		try {
			invitationService.resendInvitation(email, false);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", e.getMessage());
			return "requestInviteCode.jsp";
		}
		logger.debug("Resending invitation Code");
		return "inviteSent.jsp";
	}
	
	@RequestMapping(value = "userSignUp", method = RequestMethod.GET)
	public String userSignup(ModelMap model, Principal principal) {
		return "signup.jsp";
	}
	
	@RequestMapping(value = "finishUserSignUp", method = RequestMethod.POST)
	@Transactional
	public String finishuserSignup(HttpServletRequest request, 
	        @RequestParam(value="email", required=true) String email,
	        @RequestParam(value="firstname", required=true) String firstname,
	        @RequestParam(value="lastname", required=true) String lastname,
	        @RequestParam(value="username", required=true) String username,
	        @RequestParam(value="pass1", required=true) String password1, 
	        @RequestParam(value="pass2", required=false) String password2, 
	        @RequestParam(value="token", required=true) String token, ModelMap model) throws Exception {
		
		//If the password does not match, return invalid token exception
		if (!password1.equals(password2)) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", "Password does not match with the confirm password field!!");
			return "signup.jsp";
		} 
		
		try {
			Users user = usersDAO.findByEmail(email);
			logger.debug("Details: " + email + ":" + password1 + ":" + password2 + ":" + token);
			userService.activateAndUpdateUser(email, firstname, lastname, username, password1, token, true);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", e.getMessage());
			return "signup.jsp";
		}
		logger.debug("User Activated and password updated.");
		return "login.jsp";
	}
	
	// Implement Reset Password
	@RequestMapping(value = "resetPwdStepOne", method = RequestMethod.GET)
	public String resetPwdStepOne(ModelMap model, Principal principal) {
		return "resetPasswdCode.jsp";
	}
	
	// Implement Reset Password
	@RequestMapping(value = "resetPwdStepTwo", method = RequestMethod.POST)
	public String resetPwdStepTwo(@ModelAttribute(value = "email") String email, ModelMap model, Principal principal) {
		try {
			invitationService.resendInvitation(email, true);
		} catch (Exception e) {
			logger.debug("Reset token not sent to " + email + ".");
			//model.addAttribute("error", "true");
			//model.addAttribute("exceptionMessage", e.getMessage());
			//return "landing/resetPasswdCode";
		}
		logger.debug("Resending invitation Code");
		return "resetPwdLastStep.jsp";
	}
	

	@RequestMapping(value = "resetPasswdComplete", method = RequestMethod.POST)
	public String resetPasswdComplete(HttpServletRequest request, 
	        @RequestParam(value="email", required=false) String email, 
	        @RequestParam(value="pass1", required=false) String password1, 
	        @RequestParam(value="pass2", required=false) String password2, 
	        @RequestParam(value="token", required=false) String token, ModelMap model) throws Exception {
		
		//If the password does not match, return invalid token exception
		if (!password1.equals(password2)) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", "Password does not match with the confirm password field!!");
			return "resetPwdLastStep.jsp";
		} 
		
		try {
			logger.debug("Details: " + email + ":" + password1 + ":" + password2 + ":" + token);
			userService.resetPassword(email, password1, token);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", e.getMessage());
			return "resetPwdLastStep.jsp";
		}
		logger.debug("User Activated and password updated.");
		return "login.jsp";
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/getRole", method = RequestMethod.POST)
	public String getRole(){
		//UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		
		Users user;
		try {
			user = usersDAO.findByUserName(((User) auth.getPrincipal()).getUsername());
		} catch (UserNotFoundException e) {
			user = null;
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(user);
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