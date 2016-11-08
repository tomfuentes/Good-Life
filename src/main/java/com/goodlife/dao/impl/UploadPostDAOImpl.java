package com.goodlife.dao.impl;


import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UploadPostDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UploadPost;

@Repository
public class UploadPostDAOImpl implements UploadPostDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer addUploadPost(UploadPost uploadPost)
			throws UserNotFoundException {
		
		this.sessionFactory.getCurrentSession().save(uploadPost);
		
		return uploadPost.getPostId();
	}

	@Override
	public void deleteUploadPost(Integer postId) throws UserNotFoundException {

		UploadPost uploadPost = new UploadPost();
		try{
			uploadPost = (UploadPost)this.sessionFactory.getCurrentSession().load(UploadPost.class, postId);
		}catch(ObjectNotFoundException e){
			uploadPost = (UploadPost)this.sessionFactory.getCurrentSession().get(UploadPost.class, postId);
		}
	}

	@Override
	public List<UploadPost> getUploadPostByUser(Integer userId)
			throws UserNotFoundException {

		List<UploadPost> postList;
		Query query;
		
		query = this.sessionFactory.getCurrentSession().createQuery("FROM UPLOAD_POST WHERE USR_ID = :userId");
		query.setParameter("userId", userId);
		
		postList = query.list();
		
		return postList;
	}

	@Override
	public List<UploadPost> getRecentPosts() throws UserNotFoundException {
		
		List<UploadPost> postList;
		Query query;
		
		query = this.sessionFactory.getCurrentSession().createQuery("FROM UPLOAD_POST LIMIT 100");
		
		postList = query.list();
		
		return postList;
	}

}
