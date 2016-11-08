package com.goodlife.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.model.ShortAnswerQ;
import com.goodlife.model.ShortAnswerUserAnswer;

@Repository
public class ShortAnswerUserAnswerDAOImpl implements ShortAnswerUserAnswerDAO{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private ShortAnswerQDAO shortAnswerQDAO;
	
	@Override
	public Boolean addUserAnswer(ShortAnswerUserAnswer shortAnswerUA){
		
		this.sessionFactory.getCurrentSession().saveOrUpdate(shortAnswerUA);
		
		if(getUserAnswer(shortAnswerUA.getUserId(), shortAnswerUA.getSaQId()) == null)
			return Boolean.FALSE;
		else
			return Boolean.TRUE;
	}
	

	@Override
	public ShortAnswerUserAnswer getUserAnswer(Integer userId, Integer saQId) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ShortAnswerUserAnswer.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("userId", userId),Restrictions.eqOrIsNull("saQId", saQId)));
		
		ShortAnswerUserAnswer shortAnswerUA = (ShortAnswerUserAnswer) criteria.uniqueResult();
		
		if(null == shortAnswerUA){
			return null;
		}
		else
			return shortAnswerUA;
	}

	@Override
	public Boolean approveAnswer(Integer userId, Integer saQId) {
		
		ShortAnswerUserAnswer shortAnswerUA = getUserAnswer(userId, saQId);
			
		if(shortAnswerUA == null)
			return Boolean.FALSE;
		else{
			shortAnswerUA.setAprvd(true);
			this.sessionFactory.getCurrentSession().saveOrUpdate(shortAnswerUA);
			return Boolean.TRUE;
		}
	}
	
	@Override
	public Boolean isShortAnswerSubChapComplete(Integer userId, Integer subChapId) {

		Boolean isComplete = Boolean.TRUE;
		List<ShortAnswerQ> questionList = new ArrayList<ShortAnswerQ>();
		ShortAnswerUserAnswer shortAnsUA;
		questionList = shortAnswerQDAO.getShortAnswerBySubChapter(subChapId);
		
		if(questionList == null || questionList.isEmpty())
			isComplete = Boolean.FALSE;
		else{
		    for(int i = 0; i < questionList.size(); i++){
		    	shortAnsUA = getUserAnswer(userId, questionList.get(i).getSaQId());
		    	if(shortAnsUA != null){
		    		if(shortAnsUA.isAprvd().equals(Boolean.FALSE))
		    			isComplete = Boolean.FALSE;
		    	}
		    	else{
		    		isComplete = Boolean.FALSE;
		    	}
			}
		}
		return isComplete;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ShortAnswerUserAnswer> listAllUserShortAnsBySubChap(Integer userId,
			Integer subChapId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				ShortAnswerUserAnswer.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("userId", userId),Restrictions.eqOrIsNull("subChapId", subChapId)));
		List<ShortAnswerUserAnswer> shortAnsBySubChapterList = criteria.list();
		if(shortAnsBySubChapterList == null)
			return new ArrayList<ShortAnswerUserAnswer>();
		else
			return shortAnsBySubChapterList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ShortAnswerUserAnswer> listSubmittedShortAnsBySubChap(Integer userId, Integer subChapId) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ShortAnswerUserAnswer.class);
		criteria.add(Restrictions.and(Restrictions.and(Restrictions.eqOrIsNull("userId", userId),
									  				   Restrictions.eqOrIsNull("subChapId", subChapId)),
									  				   Restrictions.eqOrIsNull("submitted", Boolean.TRUE)));
		List<ShortAnswerUserAnswer> shortAnsBySubChapterList = criteria.list();
		if(shortAnsBySubChapterList == null)
			return new ArrayList<ShortAnswerUserAnswer>();
		else
			return shortAnsBySubChapterList;
	}
	
}
