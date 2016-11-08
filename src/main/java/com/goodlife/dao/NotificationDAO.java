package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.NotificationNotFoundException;
import com.goodlife.model.Notification;

public interface NotificationDAO {
	public Notification findByNotificationId(Integer notifId) throws NotificationNotFoundException;
	public List<Notification> findByFromUserId(Integer fromUserId) throws NotificationNotFoundException;
	public List<Notification> findByToUserId(Integer toUserId) throws NotificationNotFoundException;
	public List<Notification> findByToUserIdVisible(Integer toUserId) throws NotificationNotFoundException;
	public Integer addNotification(Notification notification);
	public Boolean deleteNotification(Integer notifId);
}