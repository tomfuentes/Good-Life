package com.goodlife.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.model.Chapter;

@Repository
public class ChapterDAOImpl implements ChapterDAO{
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public Integer addChapter(Chapter chapter) {
		
		this.sessionFactory.getCurrentSession().save(chapter);
		return chapter.getChapId();
	}

	@Override
	public Boolean deleteChapter(Integer chapterId){
		
		Boolean isSuccess = Boolean.TRUE;
		Chapter chapter;
		
		try {
			chapter = findByChapterId(chapterId);
			this.sessionFactory.getCurrentSession().delete(chapter);
		} catch (ChapterNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}        
        return isSuccess;
	}

	@Override
	public Chapter findByChapterId(Integer chapterId)
			throws ChapterNotFoundException {
		
		Chapter chapter = (Chapter) this.sessionFactory.getCurrentSession().get(Chapter.class, chapterId);
		if (null == chapterId) {
        	throw new ChapterNotFoundException("Chapter: " + chapterId + ".  Not found in the database!");
        }
		return chapter;
	}

	@Override
	public Boolean updateOrder(List<Integer> chapterList){
		Chapter chapter = new Chapter();
		Boolean isSuccess = Boolean.TRUE;
		
		for(int i=0; i<chapterList.size(); i++){
			try {
				chapter = findByChapterId(chapterList.get(i));
				chapter.setOrderId(i);
				this.sessionFactory.getCurrentSession().save(chapter);
			} catch (ChapterNotFoundException e) {
				isSuccess = Boolean.FALSE;
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
	
	@Override
	public Boolean updateChapter(Chapter updatedChapter) {
		
		try{
			Chapter chapter = findByChapterId(updatedChapter.getChapId());
			chapter.setChapDescr(updatedChapter.getChapDescr());
			chapter.setChapTitle(updatedChapter.getChapTitle());
			chapter.setOrderId(updatedChapter.getOrderId());
			chapter.setPublished(updatedChapter.getPublished());
			this.sessionFactory.getCurrentSession().saveOrUpdate(chapter);
			
			return Boolean.TRUE;
			
		}catch(ChapterNotFoundException e){
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean updateTitle(Integer chapterId, String newChapterTitle) {
		
		try{
			Chapter chapter = findByChapterId(chapterId);
			chapter.setChapTitle(newChapterTitle);
			this.sessionFactory.getCurrentSession().save(chapter);
			
			return Boolean.TRUE;
			
		}catch(ChapterNotFoundException e){
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean updateDescr(Integer chapterId, String newChapterDescr){
		
		try{
			Chapter chapter = findByChapterId(chapterId);
			chapter.setChapDescr(newChapterDescr);
			this.sessionFactory.getCurrentSession().save(chapter);
			
			return Boolean.TRUE;
			
		}catch(ChapterNotFoundException e){
			return Boolean.FALSE;
		}
		
	}
	
	@Override
	public Boolean updatePublished(Integer chapterId, Boolean published){
		
		try{
			Chapter chapter = findByChapterId(chapterId);
			chapter.setPublished(published);
			this.sessionFactory.getCurrentSession().save(chapter);
			return Boolean.TRUE;
		}catch(ChapterNotFoundException e){
			return Boolean.FALSE;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> listAllChapters(){
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Chapter.class);
		List<Chapter> allChapterList = criteria.list();
		if(allChapterList == null)
			return new ArrayList<Chapter>();
		else
			return allChapterList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> listAllPublishedChapters(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Chapter.class);
		criteria.add(Restrictions.eqOrIsNull("published", true));
		List<Chapter> publishedChapterList = criteria.list();
		if(publishedChapterList == null)
			return new ArrayList<Chapter>();
		else
			return publishedChapterList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> listAllChapterDrafts() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Chapter.class);
		criteria.add(Restrictions.eqOrIsNull("published", false));
		List<Chapter> chapterDraftList = criteria.list();
		return chapterDraftList;
	}
}
