package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UploadAnsLikeDAO;
import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.CommentLike;
import com.goodlife.model.UploadAnsLike;

@Repository
public class UploadAnsLikeDAOImpl implements UploadAnsLikeDAO {
	
	private static final String FIND_WHO_LIKES = "from UPLOAD_ANS_LIKE where flgd_by = :userid";
	
	private static final String FIND_BY_ANS = "from UPLOAD_ANS_LIKE where ans_id = :ansId";
	
	private static final String FIND_N_LIKES_BY_ANS = 
			"select count(ans_id) from UPLOAD_ANS_LIKE where ans_id = :ansId";
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<UploadAnsLike> findLikedBy(Integer userid)
			throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_WHO_LIKES);
		query.setParameter("userid", userid);
		List<UploadAnsLike> likeList = query.list();
		return likeList;
	}

	@Override
	public List<UploadAnsLike> findUploadAnsLikebyAnsId(Integer id)
			throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_BY_ANS);
		query.setParameter("ansId", id);
		List<UploadAnsLike> likeList = query.list();
		return likeList;
	}

	@Override
	public Integer addUploadAnsLike(UploadAnsLike uploadAnsLike) {
		UploadAnsLike savedLike = (UploadAnsLike) this.sessionFactory.getCurrentSession().save(uploadAnsLike);
		return savedLike.getlikeId();
	}

	@Override
	public Integer nLikeByAnsId(Integer id) throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_N_LIKES_BY_ANS);
		query.setParameter("ansId", id);
		List<Integer> likeList = query.list();
		return likeList.get(0);
	}

}
