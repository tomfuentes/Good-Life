package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ChapterPageDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.ChapterPageNotFoundException;
import com.goodlife.dao.ChapterDAO;
import com.goodlife.model.ChapterPage;

@Repository
public class ChapterPageDAOImpl implements ChapterPageDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ChapterDAO chapterDAO;
	
	@Override
	public Integer addChapterPage(ChapterPage chapterPage) {
		try {
			chapterDAO.findByChapterId(chapterPage.getChapId());
			this.sessionFactory.getCurrentSession().save(chapterPage);
			return chapterPage.getPageId();
		} catch (ChapterNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteChapterPage(Integer pageId) {
		
		Boolean isSuccess = Boolean.TRUE;
		ChapterPage chapterPage;
		try {
			chapterPage = findByPageId(pageId);
			this.sessionFactory.getCurrentSession().delete(chapterPage);
			
		} catch (ChapterPageNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public ChapterPage findByPageId(Integer pageId)
			throws ChapterPageNotFoundException {

		ChapterPage chapterPage = (ChapterPage) this.sessionFactory.getCurrentSession().get(ChapterPage.class, pageId);
		if (null == pageId) {
        	throw new ChapterPageNotFoundException("Chapter Page: " + pageId + ".  Not found in the database!");
        }
		return chapterPage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChapterPage> findAllChapterPagesByChapterId(Integer chapterId)
			throws ChapterPageNotFoundException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ChapterPage.class);
		criteria.add(Restrictions.eqOrIsNull("chapId", chapterId));
		List<ChapterPage> chapterPageList = criteria.list();
		if(null == chapterPageList){
			throw new ChapterPageNotFoundException("Chapter Pages with chapter ID: " + chapterId + " not found in the database!");
		}
		return chapterPageList;
	}

	@Override
	public Boolean updateChapterPageOrder(List<Integer> chapterPageList) {
		ChapterPage chapterPage = new ChapterPage();
		try{
			for(int i=0; i<chapterPageList.size(); i++){
				chapterPage = (ChapterPage)this.sessionFactory.getCurrentSession().get(ChapterPage.class,chapterPageList.get(i));
				chapterPage.setPageNum(i+1);
				this.sessionFactory.getCurrentSession().save(chapterPage);
			}
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean updatePageUrl(Integer pageId, String newUrl) {
		Boolean isSuccess = Boolean.TRUE;
		ChapterPage chapterPage;
		try {
			chapterPage = findByPageId(pageId);
			chapterPage.setPageUrl(newUrl);
			this.sessionFactory.getCurrentSession().save(chapterPage);
		} catch (ChapterPageNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public Boolean deleteAllPagesByChapterId(Integer chapterId) {

		List<ChapterPage> chapterPageDeleteList;
		try {
			chapterPageDeleteList = findAllChapterPagesByChapterId(chapterId);
			for(int i = 0; i < chapterPageDeleteList.size(); i++){
				deleteChapterPage(chapterPageDeleteList.get(i).getPageId());
			}
			return Boolean.TRUE;
		} catch (ChapterPageNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}		
	}

	@Override
	public Boolean setPublishChapterPage(Integer pageId, Boolean published) {
		
		Boolean isSuccess;
		try {
			ChapterPage chapterPage = findByPageId(pageId);
			chapterPage.setPublished(published);
			this.sessionFactory.getCurrentSession().saveOrUpdate(chapterPage);
			isSuccess = Boolean.TRUE;
		} catch (ChapterPageNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		
		return isSuccess;		
	}

}
