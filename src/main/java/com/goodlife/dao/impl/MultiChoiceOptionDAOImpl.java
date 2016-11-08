package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.exceptions.MultipleChoiceOptionNotFoundException;
import com.goodlife.model.MultiChoiceOption;

@Repository
public class MultiChoiceOptionDAOImpl implements MultiChoiceOptionDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer addMultiChoiceOption(MultiChoiceOption multiChoiceOption) {
		
		this.sessionFactory.getCurrentSession().save(multiChoiceOption);
		
		return multiChoiceOption.getOptionId();
	}

	@Override
	public Boolean updateChoiceText(Integer optionId, String text){
		
		Boolean isSuccess = Boolean.TRUE;
		MultiChoiceOption multiChoiceOption;
		
		try {
			multiChoiceOption = findMultiChoiceOptionById(optionId);
			multiChoiceOption.setChoiceText(text);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoiceOption);
		} catch (MultipleChoiceOptionNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
			
		return isSuccess;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MultiChoiceOption> getMultiChoiceOptions(Integer multiQuesId){

		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceOption.class);
		criteria.add(Restrictions.eqOrIsNull("multiQuesId", multiQuesId));
		List<MultiChoiceOption> multiChoiceList = criteria.list();
		
		return multiChoiceList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MultiChoiceOption> getPublishedMultiChoiceOptions(Integer multiQuesId){

		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceOption.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("multiQuesId", multiQuesId),Restrictions.eq("published",true)));
		List<MultiChoiceOption> multiChoiceList = criteria.list();
		
		return multiChoiceList;
	}

	@Override
	public Boolean deleteMultiChoiceOption(Integer optionId){
		
		Boolean isSuccess = Boolean.TRUE;
		MultiChoiceOption mcOpt;
		
		try {
			mcOpt = findMultiChoiceOptionById(optionId);
			this.sessionFactory.getCurrentSession().delete(mcOpt);
		} catch (MultipleChoiceOptionNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public MultiChoiceOption findMultiChoiceOptionById(Integer optionId)
			throws MultipleChoiceOptionNotFoundException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceOption.class);
		criteria.add(Restrictions.eqOrIsNull("optionId", optionId));
		MultiChoiceOption mcOpt = (MultiChoiceOption) criteria.uniqueResult();
		if (null == mcOpt) {
        	throw new MultipleChoiceOptionNotFoundException("MultiChoice Option: " + optionId + ".  Not found in the database!");
        }
		return mcOpt;
	}

	@Override
	public Boolean setPublishMulitChoiceOption(Integer optionId,
			Boolean published) {

		Boolean isSuccess;
		try {
			MultiChoiceOption multiChoiceOption = findMultiChoiceOptionById(optionId);
			multiChoiceOption.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoiceOption);
			isSuccess = Boolean.TRUE;
		} catch (MultipleChoiceOptionNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}

}
