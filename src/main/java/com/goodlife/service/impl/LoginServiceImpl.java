package com.goodlife.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodlife.dao.UsersDAO;
import com.goodlife.model.Users;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	UsersDAO loginDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Users user = loginDao.findByUserName(username);
			Set<String> roles = new HashSet<String>();
			roles.add(returnRole(String.valueOf(user.getRoleTypeCode())));
			List<GrantedAuthority> authority = buildUserAuthority(roles);
			return buildUserForAuthentication(user, authority);
		} catch (Exception e) {
			throw new UsernameNotFoundException(username);
		}
	}
	
	private String returnRole(String characterRole) {
		if (characterRole.equals("S"))
				return "ROLE_STUDENT";
		if (characterRole.equals("F"))
				return "ROLE_FACILITATOR";
		if (characterRole.equals("A"))
				return "ROLE_SUPERADMIN";
		if (characterRole.equals("M"))
				return "ROLE_MODERATOR";
		if (characterRole.equals("T"))
				return "ROLE_TRAINER";
		return null;
	}
	
	private User buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
				user.isRegistered(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<String> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// Build user's authorities
		for (String userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
}
