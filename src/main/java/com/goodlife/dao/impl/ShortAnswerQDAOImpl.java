package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ShortAnswerQDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.exceptions.ShortAnswerNotFoundException;
import com.goodlife.model.ShortAnswerQ;

@Repository
public class ShortAnswerQDAOImpl implements ShortAnswerQDAO{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private SubChapterDAO subChapterDAO;
	
	@Override
	public Integer addShortAnswerQuestion(ShortAnswerQ shortAnswerQ) {
		
		this.sessionFactory.getCurrentSession().saveOrUpdate(shortAnswerQ);
		
		return shortAnswerQ.getSaQId();
	}

	@Override
	public Boolean updateQuestionText(Integer saQId, String question) {
		Boolean isSuccess = Boolean.TRUE;
		ShortAnswerQ shortAns;
		try {
			shortAns = getShortAnswerById(saQId);
			shortAns.setQuestion(question);
			this.sessionFactory.getCurrentSession().save(shortAns);
		} catch (ShortAnswerNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public Boolean updateHelpText(Integer saQId, String helpText) {
		Boolean isSuccess = Boolean.TRUE;
		ShortAnswerQ shortAns;
		try {
			shortAns = getShortAnswerById(saQId);
			shortAns.setHelpText(helpText);
			this.sessionFactory.getCurrentSession().save(shortAns);
		} catch (ShortAnswerNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public Boolean updateOrderId(List<Integer> saQIdList) {
		Boolean isSuccess = Boolean.TRUE;
		ShortAnswerQ shortAns = new ShortAnswerQ();
		
		for(int i = 0; i <saQIdList.size(); i++){
			try {
				shortAns = getShortAnswerById(saQIdList.get(i));
				shortAns.setOrderId(i+1);
				this.sessionFactory.getCurrentSession().saveOrUpdate(shortAns);
			} catch (ShortAnswerNotFoundException e) {
				isSuccess = Boolean.FALSE;
				e.printStackTrace();
			}
		}
		return isSuccess;
	}

	@Override
	public ShortAnswerQ getShortAnswerById(Integer saQId) 
		throws ShortAnswerNotFoundException {
		
		ShortAnswerQ shortAnswerQ = new ShortAnswerQ();
		try{
			shortAnswerQ = (ShortAnswerQ)this.sessionFactory.getCurrentSession().load(ShortAnswerQ.class, saQId);
		}catch(ObjectNotFoundException e){
			shortAnswerQ = (ShortAnswerQ)this.sessionFactory.getCurrentSession().get(ShortAnswerQ.class, saQId);
		}
		if(null == shortAnswerQ){
			throw new ShortAnswerNotFoundException("Short Answer Question Id: " + saQId + " not found in the database!");
		}
		return shortAnswerQ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShortAnswerQ> getShortAnswerBySubChapter(Integer subChapId){
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ShortAnswerQ.class);
		criteria.add(Restrictions.eqOrIsNull("subChapId", subChapId));
		
		List<ShortAnswerQ> shortAnsList = criteria.list();
		return shortAnsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShortAnswerQ> getPublishedShortAnswerBySubChapter(Integer subChapId) {

		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ShortAnswerQ.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("subChapId", subChapId),Restrictions.eq("published", true)));
		
		List<ShortAnswerQ> shortAnsList = criteria.list();
		
		return shortAnsList;
	}

	@Override
	public Boolean setPublishShortAnswer(Integer saQId, Boolean published) {

		Boolean isSuccess;
		try {
			ShortAnswerQ shortAnswerQ = getShortAnswerById(saQId);
			shortAnswerQ.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(shortAnswerQ);
			isSuccess = Boolean.TRUE;
		} catch (ShortAnswerNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}
}
