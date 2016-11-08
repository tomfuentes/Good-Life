package com.goodlife.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import com.goodlife.model.UserStatus;

public interface UserStatusDAO{
	
	public List<UserStatus> findByUserId(Integer userId);
	public List<UserStatus> findByStatusCode(char statusTypeCode);
	public List<UserStatus> findCurrentSuspendedUsers();
	public Integer findNumberofSuspensionsByUserId(Integer userId);
	public Integer addUserStatus(UserStatus userStatus);
	public Boolean changeEndDate(Integer userStatusId, Date newEndDate);
	public Boolean deleteUserStatus(Integer userStatusId);
	public UserStatus findByUserStatusId(Integer userStatusId) throws ObjectNotFoundException;
	public UserStatus findCurrentStatusByUser(Integer userId);
}
