package com.goodlife.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UploadFileQDAO;
import com.goodlife.model.UploadFileQ;

@Repository
public class UploadFileQDAOImpl implements UploadFileQDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addUploadFileQuestion(UploadFileQ uploadFileQ) {

		this.sessionFactory.getCurrentSession().saveOrUpdate(uploadFileQ);
		return uploadFileQ.getUploadQuesId();
	}
	
	@Override
	public Boolean updateDescription(Integer uploadQuesId, String description){

		try{
			UploadFileQ uploadFileQ = getUploadFileQuestion(uploadQuesId);
			uploadFileQ.setDescription(description);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean updateHelpText(Integer uploadQuesId, String helpText){

		try{
			UploadFileQ uploadFileQ = getUploadFileQuestion(uploadQuesId);
			uploadFileQ.setHelpText(helpText);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@Override
	public UploadFileQ getUploadFileQuestion(Integer uploadFileQId) throws ObjectNotFoundException {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UploadFileQ.class);
		criteria.add(Restrictions.eqOrIsNull("uploadQuesId", uploadFileQId));
		UploadFileQ uploadFileQ = (UploadFileQ) criteria.uniqueResult();
		if (uploadFileQ == null) {
			throw new ObjectNotFoundException(null, "Upload question with id " + uploadFileQId + " not found.");
		}
		return uploadFileQ;
	}

	@Override
	public UploadFileQ getUploadFileQBySubchapId(Integer subChapId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UploadFileQ.class);
		criteria.add(Restrictions.eqOrIsNull("subChapId", subChapId));
		UploadFileQ quesList = (UploadFileQ) criteria.uniqueResult();
		return quesList;
	}

	@Override
	public UploadFileQ getPublishedUploadFileQBySubchapId(Integer subChapId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(UploadFileQ.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("subChapId", subChapId),Restrictions.eq("published", true)));
		UploadFileQ quesList = (UploadFileQ) criteria.uniqueResult();
		return quesList;
	}
	
	@Override
	public Boolean deleteUploadFileQuestion(Integer uploadQuesId){
		try{
			UploadFileQ uploadFileQ = getUploadFileQuestion(uploadQuesId);
			this.sessionFactory.getCurrentSession().delete(uploadFileQ);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean setPublishedUploadFileQ(Integer uploadFileQId, Boolean published){
		
		try{
			UploadFileQ uploadFileQ = getUploadFileQuestion(uploadFileQId);
			uploadFileQ.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(uploadFileQ);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

}
