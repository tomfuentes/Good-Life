package com.goodlife.dao.impl;

import java.util.List;

import com.goodlife.dao.NotificationDAO;
import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.exceptions.NotificationNotFoundException;
import com.goodlife.model.MultiChoiceQ;
import com.goodlife.model.Notification;
import com.goodlife.exceptions.NotificationNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class NotificationDAOImpl implements NotificationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Notification findByNotificationId(Integer notifId) throws NotificationNotFoundException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Notification.class);
        criteria.add(Restrictions.and(Restrictions.eqOrIsNull("notifId", notifId)));
        Notification notif = (Notification) criteria.uniqueResult();
        if(notif == null)
            return null;
        else
            return notif;
	}

	@Override
	public List<Notification> findByFromUserId(Integer fromUserId) throws NotificationNotFoundException {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Notification.class);
		criteria.add(Restrictions.eqOrIsNull("fromUserId", fromUserId));
		List<Notification> notifList = criteria.list();
		if(notifList == null)
			return new ArrayList<Notification>();
		return notifList;
	}

	@Override
	public List<Notification> findByToUserId(Integer toUserId) throws NotificationNotFoundException {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Notification.class);
		criteria.add(Restrictions.eqOrIsNull("toUserId", toUserId));
		List<Notification> notifList = criteria.list();
		if(notifList == null)
			return new ArrayList<Notification>();
		return notifList;
	}

	@Override
	public List<Notification> findByToUserIdVisible(Integer toUserId) throws NotificationNotFoundException {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Notification.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("toUserId", toUserId), Restrictions.eq("visibility", true)));
		List<Notification> notifList = criteria.list();
		if(notifList == null)
			return new ArrayList<Notification>();
		return notifList;
	}

	@Override
	public Integer addNotification(Notification notification) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(notification);
		return notification.getNotificationId();
	}

	@Override
	public Boolean deleteNotification(Integer notifId) {
		Boolean isSuccess = Boolean.TRUE;
		try {
			Notification notif = findByNotificationId(notifId);
			this.sessionFactory.getCurrentSession().saveOrUpdate(notif);
		} catch (NotificationNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}

}
