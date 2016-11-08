package com.goodlife.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.MultiChoiceListDAO;
import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceList;

@Repository
public class MultiChoiceListDAOImpl implements MultiChoiceListDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private MultiChoiceQDAO multiChoiceQDAO;

	@Override
	public Integer addMultiChoiceList(MultiChoiceList multiChoiceList) {
		
		this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoiceList);
		return multiChoiceList.getMultiChoiceListId();
	}

	@Override
	public Boolean deleteMultiChoiceList(Integer multiChoiceListId) {
		
		Boolean isSuccess = Boolean.TRUE;
		this.sessionFactory.getCurrentSession().delete(multiChoiceListId);
		try {
			getMultiChoiceListById(multiChoiceListId);
			isSuccess = Boolean.FALSE;
		} catch (MultipleChoiceNotFoundException e) {
			e.printStackTrace();
			isSuccess = Boolean.TRUE;
		}
		
		return isSuccess && multiChoiceQDAO.deleteAllMultiChoiceByList(multiChoiceListId);
	}

	@Override
	public Boolean updateOrder(List<Integer> multiChoiceListIdArray) {
		
		MultiChoiceList multiList;
		Boolean isSuccess = Boolean.TRUE;
		
		for(int i = 0; i < multiChoiceListIdArray.size(); i++){
			try {
				multiList = getMultiChoiceListById(multiChoiceListIdArray.get(i));
				multiList.setOrderId(i+1);
				this.sessionFactory.getCurrentSession().saveOrUpdate(multiList);
			} catch (MultipleChoiceNotFoundException e) {
				isSuccess = Boolean.FALSE;
				e.printStackTrace();
			}
		}
		return isSuccess;
	}

	@Override
	public Boolean updateTitle(Integer multiChoiceListId, String title) {
		MultiChoiceList multiList;
		try {
			multiList = getMultiChoiceListById(multiChoiceListId);
			multiList.setTitle(title);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiList);
			return Boolean.TRUE;
		} catch (MultipleChoiceNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean updateDescription(Integer multiChoiceListId,
			String description) {
		MultiChoiceList multiList;
		try {
			multiList = getMultiChoiceListById(multiChoiceListId);
			multiList.setDescription(description);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiList);
			return Boolean.TRUE;
		} catch (MultipleChoiceNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MultiChoiceList> getAllMultiChoiceLists(Integer subChapId) {

		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceList.class);
		criteria.add(Restrictions.eqOrIsNull("subChapId", subChapId));
		
		List<MultiChoiceList> multiList = criteria.list();
		if(multiList == null)
			return new ArrayList<MultiChoiceList>();
		else
			return criteria.list();
	}

	@Override
	public MultiChoiceList getMultiChoiceListById(Integer multiChoiceListId)
			throws MultipleChoiceNotFoundException {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceList.class);
		criteria.add(Restrictions.eqOrIsNull("multiChoiceListId", multiChoiceListId));
		
		MultiChoiceList multiList = (MultiChoiceList) criteria.uniqueResult();
		if(multiList == null)
			throw new MultipleChoiceNotFoundException("MultiChoiceList with Id: " + multiChoiceListId + " not found.");
		else
			return multiList;			
	}

	@Override
	public Boolean setGradedMultiChoiceQ(Integer multiChoiceListId,
			Boolean graded) {
		
		MultiChoiceList multiList;
		try {
			multiList = getMultiChoiceListById(multiChoiceListId);
			multiList.setGraded(graded);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiList);
			return Boolean.TRUE;
		} catch (MultipleChoiceNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean setPublishMultiChoiceQ(Integer multiChoiceListId,
			Boolean published) {
		
		MultiChoiceList multiList;
		try {
			multiList = getMultiChoiceListById(multiChoiceListId);
			multiList.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiList);
			return Boolean.TRUE;
		} catch (MultipleChoiceNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MultiChoiceList> getAllPublishedMultiChoiceLists(
			Integer subChapId) {

		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceList.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("subChapId", subChapId),Restrictions.eq("published", Boolean.TRUE)));
		
		List<MultiChoiceList> multiList = criteria.list();
		if(multiList == null)
			return new ArrayList<MultiChoiceList>();
		else
			return criteria.list();
	}
	
}