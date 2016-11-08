package com.goodlife.controller;

import java.security.Principal;







import javax.servlet.http.HttpServletRequest;

//Import log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

@Controller
public class LoginController {
	static final Logger logger = LogManager.getLogger(LoginController.class.getName());

	@Autowired
	private UsersDAO usersDAO;
	
	@Transactional
	@RequestMapping(value = "/welcome")
	public String printWelcome(HttpServletRequest httpRequest, ModelMap model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("test", "blank");
		logger.warn("Landing on the hello page");
		try {
			Users user = usersDAO.findByUserName(name);
			if(user.getRoleTypeCode() == Character.valueOf('A'))
				return "/index.html";
			else
				return "/index.html";// + user.getUserId();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return "login.jsp";
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		logger.debug("Login page hit");
		return "login.jsp";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		logger.error("Error occured.  Sending back to login page");
		model.addAttribute("error", "true");
		return "login.jsp";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login.jsp";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		logger.error("hitting the homepage");
		return "login.jsp";
	}

}