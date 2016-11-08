package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Users;

public interface UsersDAO 
{
	public Users findByUserName(String username) throws UserNotFoundException;
	public List<Users> findByFirstName(String firstname);
	public List<Users> findByLastName(String lastname);
	public List<Users> findByRoleTypes(List<Character> roles);
	public Users findByEmail(String email) throws UserNotFoundException;
	public List<Users> findByCity(String city);
	public List<Users> findByState(String state);
	public List<Users> advancedQuery(String input, String field, List<Character> roles);
	public Integer addUser(Users user);
	public Boolean deleteUser(String username);
	public Boolean disableUser(String username);
	public Boolean enableUser(String username);
	public Boolean promoteUser(String username, char roleTypeCode);
	public Users findByUserId(Integer us) throws UserNotFoundException;
}