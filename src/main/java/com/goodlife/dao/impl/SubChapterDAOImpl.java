package com.goodlife.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.SubChapter;

@Repository
public class SubChapterDAOImpl implements SubChapterDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ChapterDAO chapterDAO;
	
	@Override
	public Integer addSubChapter(SubChapter subChapter){
		
		try {
			chapterDAO.findByChapterId(subChapter.getChapId());
			this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
			return subChapter.getSubChapId();
		} catch (ChapterNotFoundException e) {
			e.printStackTrace();
			return null;
		}	
	}
	@Override
	public Boolean deleteSubChapter(Integer subChapId){
		
		SubChapter subChapter;
		try {
			subChapter = getSubChapterById(subChapId);
			this.sessionFactory.getCurrentSession().delete(subChapter);
			return Boolean.TRUE;
		} catch (SubChapterNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
			
	}
	@Override
	public Boolean updateOrder(List<Integer> subChapterIdList){
		Boolean isSuccess = Boolean.TRUE;
		SubChapter subChapter = new SubChapter();
		for (int i = 0; i < subChapterIdList.size(); i++){
			try {
				subChapter = getSubChapterById(subChapterIdList.get(i));
				subChapter.setOrderId(i+1);
				this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
			} catch (SubChapterNotFoundException e) {
				isSuccess = Boolean.FALSE;
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
	
	@Override
	public Boolean updateSubChapter(SubChapter updatedSubChapter){
		try{
			SubChapter subChapter = getSubChapterById(updatedSubChapter.getSubChapId());
			subChapter.setChapId(updatedSubChapter.getChapId());
			subChapter.setSubChapDescr(updatedSubChapter.getSubChapDescr());
			subChapter.setSubChapTitle(updatedSubChapter.getSubChapTitle());
			subChapter.setOrderId(updatedSubChapter.getOrderId());
			subChapter.setPublished(updatedSubChapter.getPublished());
			this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
			
			return Boolean.TRUE;
			
		}catch(SubChapterNotFoundException e){
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean updateTitle(Integer subChapId, String subChapTitle){
		
		try {
			SubChapter subChapter = getSubChapterById(subChapId);
			subChapter.setSubChapTitle(subChapTitle);
			this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
			return Boolean.TRUE;
		} catch (SubChapterNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	@Override
	public Boolean updateDescription(Integer subChapId, String subChapDescr){
		
		try {
			SubChapter subChapter = getSubChapterById(subChapId);
			subChapter.setSubChapDescr(subChapDescr);
			this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
			return Boolean.TRUE;
		} catch (SubChapterNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SubChapter> getSubChapListByChapter(Integer chapId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubChapter.class);
		criteria.add(Restrictions.eqOrIsNull("chapId", chapId));
		List<SubChapter> subChapList = criteria.list();
		if(subChapList == null)
			return new ArrayList<SubChapter>();
		else
			return subChapList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SubChapter> getPublishedSubChapListByChap(Integer chapId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubChapter.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("chapId", chapId),Restrictions.eq("published", true)));
		List<SubChapter> subChapList = criteria.list();
		if(subChapList == null)
			return new ArrayList<SubChapter>();
		else
			return subChapList;
	}
	
	@Override
	public SubChapter getSubChapterById(Integer subChapId)
			throws SubChapterNotFoundException {

		SubChapter subChapter;
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(SubChapter.class);
		criteria.add(Restrictions.eqOrIsNull("subChapId", subChapId));
		subChapter = (SubChapter) criteria.uniqueResult();
		if(null == subChapter){
			throw new SubChapterNotFoundException("subChapId: " + subChapId + " not found in the database!");
		}
		return subChapter;
	}
	@Override
	public Boolean setPublishSubChapter(Integer subChapId, Boolean published) {

		Boolean isSuccess;
		try {
			SubChapter subChapter = getSubChapterById(subChapId);
			subChapter.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(subChapter);
			isSuccess = Boolean.TRUE;
		} catch (SubChapterNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}
}
