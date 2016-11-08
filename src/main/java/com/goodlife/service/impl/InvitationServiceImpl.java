package com.goodlife.service.impl;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//import com.goodlife.dao.UserRoleDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserAlreadyExistsException;
import com.goodlife.exceptions.UserNotFoundException;
//import com.goodlife.model.UserRole;
import com.goodlife.model.Users;
import com.goodlife.service.InvitationService;
import com.goodlife.service.util.ApplicationMailer;

@Service("invitationService")
@Transactional
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	UsersDAO usersDao;
	
	@Autowired
	ApplicationMailer mailer;

	private String tempPassword = "Temp$Password";
	private int RandomMin = 100001;
	private int RandomMax = 999999;

	public void inviteUserByEmail(String email, String loggedInUser)
			throws UserAlreadyExistsException, UserNotFoundException {
		Integer randomNumber;
		Users newUser = usersDao.findByEmail(email);
		if (newUser != null) {
			throw new UserAlreadyExistsException("Email " + email
					+ " already exists!  Ask user to signup if not already done.  If invitation code has been misplaced, a new one can be requested.");
		}

		randomNumber = generateRandomNumber(RandomMin, RandomMax);

		newUser = new Users();
		newUser.setEmail(email);
		newUser.setRegistered(false);
		newUser.setPassword(tempPassword);
		newUser.setInvitedBy(loggedInUser);
		newUser.setInvitedDate(new Date());
		newUser.setInvitationCode(randomNumber);
		
		newUser.setRoleTypeCode('S');
		
		/*UserRole userRole = new UserRole();

		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_STUDENT");
		userRole.setUser(newUser);
		
		userRoleDao.addUserRole(userRole);*/

		usersDao.addUser(newUser);

		String subject = "Register Your Account";
		String body = "Hello " + email + ","
					+ "\n\nWelcome to the Good Life Organization Curriculum Tool."
					+ " Please use the invite code below to register your account and begin your work."
					+ "\n\nInvite code: " + randomNumber
					+ "\n\nThank you,\nThe Good Life Organization Team";
		mailer.sendMail(email, subject, body);

	}

	/**
	 * Returns a pseudo-random number between min and max, inclusive. The
	 * difference between min and max can be at most
	 * Integer.MAX_VALUE - 1
	 *
	 * @param min
	 *            Minimum value
	 * @param max
	 *            Maximum value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random.nextInt(int)
	 */
	private Integer generateRandomNumber(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	@Override
	public void deleteUser(String username) throws UserNotFoundException{
			usersDao.deleteUser(username);
	}

	@Override
	public void disableUser(String username)  throws UserNotFoundException {
			usersDao.disableUser(username);
	}

	@Override
	public void enableUser(String username) throws UserNotFoundException {
			usersDao.enableUser(username);
	}

	@Override
	public void resendInvitation(String email, boolean resetPassword) throws UserAlreadyExistsException, UserNotFoundException {
		Users user = usersDao.findByEmail(email);
		
		if (user==null) {
			throw new UserNotFoundException("Email entered does not exist in our database.");
		}
		
		if (!resetPassword && user.isRegistered()) {
			throw new UserAlreadyExistsException("The user you are trying to signup with already exists and is active.  If you forgot your password, we recommend using the reset password link.");
		}
		
		Integer randomNumber = generateRandomNumber(RandomMin, RandomMax);
		user.setInvitationCode(randomNumber);
		usersDao.addUser(user);
		String subject = "Reset Password";
		String body = "Hello " + user.getUsername() + ","
					+ "\n\nTo reset your password use the new invite code below."
					+ "\n\nInvite Code is " + randomNumber
					+ "\n\nThank you,"
					+ "\nThe Good Life Organization Team";
		mailer.sendMail(user.getEmail(), subject, body);
	}

}
