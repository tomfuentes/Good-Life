package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UploadPostLikeDAO;
import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.UploadPostLike;

@Repository
public class UploadPostLikeDAOImpl implements UploadPostLikeDAO {
	
	private static final String FIND_WHO_LIKES = "from UPLOAD_POST_LIKE where flgd_by = :userid";
	
	private static final String FIND_BY_POST = "from UPLOAD_POST_LIKE where pst_id = :postId";
	
	private static final String FIND_N_LIKES_BY_POST = 
			"select count(pst_id) from UPLOAD_POST_LIKE where pst_id = :postId";
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<UploadPostLike> findLikedBy(Integer userid)
			throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_WHO_LIKES);
		query.setParameter("userid", userid);
		List<UploadPostLike> likeList = query.list();
		return likeList;
	}

	@Override
	public List<UploadPostLike> findUploadPostLikebyPostId(Integer id)
			throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_BY_POST);
		query.setParameter("postId", id);
		List<UploadPostLike> likeList = query.list();
		return likeList;
	}

	@Override
	public Integer addUploadPostLike(UploadPostLike uploadPostLike) {
		UploadPostLike savedLike = (UploadPostLike) this.sessionFactory.getCurrentSession().save(uploadPostLike);
		return savedLike.getlikeId();
	}

	@Override
	public Integer nLikeByPostId(Integer id) throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_N_LIKES_BY_POST);
		query.setParameter("postId", id);
		List<Integer> likeList = query.list();
		return likeList.get(0);
	}

}
