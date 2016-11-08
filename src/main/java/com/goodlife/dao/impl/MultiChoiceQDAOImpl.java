package com.goodlife.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceQ;

@Repository
public class MultiChoiceQDAOImpl implements MultiChoiceQDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addMultiChoice(MultiChoiceQ multiChoiceQ) {
		
		this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoiceQ);
		return multiChoiceQ.getMultiQuesId();
	}

	@Override
	public Boolean deleteMultiChoice(Integer multiChoiceId) {
		Boolean isSuccess = Boolean.TRUE;
		try {
			MultiChoiceQ multiChoice = getMultiChoiceQById(multiChoiceId);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoice);
		} catch (MultipleChoiceNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean deleteAllMultiChoiceByList(Integer multiChoiceListId){
		Boolean isSuccess = Boolean.TRUE;
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceQ.class);
		criteria.add(Restrictions.eq("multiChoiceListId", multiChoiceListId));
		List<MultiChoiceQ> multiQList = criteria.list();
		
		for(int i = 0; i < multiQList.size(); i++){
			if(deleteMultiChoice(multiQList.get(i).getMultiQuesId()) == Boolean.FALSE)
				isSuccess = Boolean.FALSE;
		}
		return isSuccess;
	}

	@Override
	public Boolean updateOrder(List<Integer> multiChoiceIdList) {
		Boolean isSuccess = Boolean.TRUE;
		MultiChoiceQ multiChoice = new MultiChoiceQ();
		for (int i = 0; i < multiChoiceIdList.size(); i++){
			try {
				multiChoice = getMultiChoiceQById(multiChoiceIdList.get(i));
				multiChoice.setOrderId(i+1);
				this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoice);
			} catch (MultipleChoiceNotFoundException e) {
				isSuccess = Boolean.FALSE;
				e.printStackTrace();
			}
		}
		return isSuccess;
	}

	@Override
	public Boolean updateQuestionText(Integer multiChoiceId, String quesText) {
		Boolean isSuccess = Boolean.TRUE;
		MultiChoiceQ multiChoice;
		try {
			multiChoice = getMultiChoiceQById(multiChoiceId);
			multiChoice.setQuesText(quesText);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoice);
		} catch (MultipleChoiceNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public Boolean updateHelpText(Integer multiChoiceId, String helpText){
		Boolean isSuccess = Boolean.TRUE;
		MultiChoiceQ multiChoice;
		try {
			multiChoice = getMultiChoiceQById(multiChoiceId);
			multiChoice.setHelpText(helpText);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoice);
		} catch (MultipleChoiceNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public Boolean updateCorrectAnswer(Integer multiChoiceId, Integer correctAnswer) {
		Boolean isSuccess = Boolean.TRUE;
		MultiChoiceQ multiChoice;
		try {
			multiChoice = getMultiChoiceQById(multiChoiceId);
			multiChoice.setCorrectAnswer(correctAnswer);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoice);
		} catch (MultipleChoiceNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MultiChoiceQ> getAllMultiChoice(Integer multiChoiceListId) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceQ.class);
		criteria.add(Restrictions.eqOrIsNull("multiChoiceListId", multiChoiceListId));
		List<MultiChoiceQ> multiChoiceList = criteria.list();
		if(multiChoiceList == null)
			return new ArrayList<MultiChoiceQ>();
		return multiChoiceList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MultiChoiceQ> getAllPublishedMultiChoice(Integer multiChoiceListId) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceQ.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("multiChoiceListId", multiChoiceListId),Restrictions.eq("published", true)));
		List<MultiChoiceQ> multiChoiceList = criteria.list();
		return multiChoiceList;
	}
	
	@Override
	public MultiChoiceQ getMultiChoiceQById(Integer multiChoiceId) throws MultipleChoiceNotFoundException{
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceQ.class);
		criteria.add(Restrictions.eqOrIsNull("multiQuesId", multiChoiceId));
		MultiChoiceQ multiChoice = (MultiChoiceQ) criteria.uniqueResult();
		if(null == multiChoice){
			throw new MultipleChoiceNotFoundException("Multiple Choice Id: " + multiChoiceId + " not found in database!");
		}
		
		return multiChoice;
	}

	@Override
	public Boolean setPublishMultiChoiceQ(Integer multiChoiceId,
			Boolean published) {

		Boolean isSuccess;
		try {
			MultiChoiceQ multiChoiceQ = getMultiChoiceQById(multiChoiceId);
			multiChoiceQ.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoiceQ);
			isSuccess = Boolean.TRUE;
		} catch (MultipleChoiceNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}
	

}