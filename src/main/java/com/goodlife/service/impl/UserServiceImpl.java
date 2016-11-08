package com.goodlife.service.impl;


import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;








import com.goodlife.dao.InstructorDAO;
import com.goodlife.dao.StudentDAO;
import com.goodlife.dao.SuperAdminDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.InvalidEmailToken;
import com.goodlife.exceptions.UserAlreadyExistsException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;
import com.goodlife.model.Student;
import com.goodlife.model.Users;
import com.goodlife.service.UserService;
import com.goodlife.service.util.PasswordEncoder;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

	@Autowired
	SuperAdminDAO superAdminDAO;
	
	@Autowired
	UsersDAO usersDao;
	
	@Autowired
	StudentDAO studentDao;
	
	@Autowired
	InstructorDAO instructorDao;
	
	@Autowired
	PasswordEncoder passwdEncoder;



	public void activateAndUpdateUser(String email, String firstname, String lastname, String username, String passwd, String token, boolean resetPassword) 
			throws InvalidEmailToken, UserAlreadyExistsException, UserNotFoundException {
		Users user = usersDao.findByEmail(email);
		if (!user.getInvitationCode().toString().equals(token)) {
			throw new InvalidEmailToken("Email token does not match!!");
		}
		
		if (!resetPassword && user.isRegistered()) {
			throw new UserAlreadyExistsException("The user you are trying to signup with already exists and is active.  If you forgot your password, we recommend using the reset password link.");
		}
		user.setRegistered(true);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUsername(username);
		user.setPassword(passwdEncoder.encodePassword(passwd));
		usersDao.addUser(user);
		
		if(Character.toUpperCase(user.getRoleTypeCode()) == 'I'){
			instructorDao.addInstructor(user.getUserId());
		}
		else if(Character.toUpperCase(user.getRoleTypeCode()) == 'S'){
			Student student = new Student();
			student.setCurrentChapterId(1);
			student.setStartDate(new Date());
			student.setUserId(user.getUserId());
			studentDao.addStudent(student);
		}
	}
	
	public void resetPassword(String email, String passwd, String token) throws InvalidEmailToken, UserNotFoundException{
		Users user = usersDao.findByEmail(email);
		if(user == null)
			throw new UserNotFoundException("Email does not match any records!");
		if(!user.getInvitationCode().toString().equals(token))
			throw new InvalidEmailToken("Email token does not match1!");
		user.setPassword(passwdEncoder.encodePassword(passwd));		
	}

	
	public Users findByUserName(String username) throws UserNotFoundException {
		return usersDao.findByUserName(username);
	}

	public List<Users> findByFirstName(String firstname) throws UserNotFoundException {
		return usersDao.findByFirstName(firstname);
	}

	public Users findByEmail(String email) throws UserNotFoundException {
		return usersDao.findByEmail(email);
	}

	public List<Users> findByRoleTypes(List<Character> roles) throws UserNotFoundException {
		return usersDao.findByRoleTypes(roles);
	}

	public List<Users> findByLastName(String lastname) throws UserNotFoundException {
		return usersDao.findByLastName(lastname);
	}
	
	public List<Users> findByCity(String city) throws UserNotFoundException {
		return usersDao.findByCity(city);
	}
	
	public List<Users> findByState(String state) throws UserNotFoundException {
		return usersDao.findByState(state);
	}

	public List<Student> findStudentByInstructorId(Integer instructorId) throws UserNotFoundException {
		return studentDao.findStudentByInstructorId(instructorId);
	}


	@Override
	public List<Users> advancedQuery(String input, String field, List<Character> roles) 
		throws UserNotFoundException {
		return usersDao.advancedQuery(input, field, roles);
	}
}
