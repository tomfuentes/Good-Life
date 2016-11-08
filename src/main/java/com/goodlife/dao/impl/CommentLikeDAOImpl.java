package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.CommentLikeDAO;
import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.CommentLike;

@Repository
public class CommentLikeDAOImpl implements CommentLikeDAO {
	
	private static final String FIND_WHO_LIKES = 
			"from COMMENT_LIKE where lkd_by = :userId";
	
	private static final String FIND_BY_COMMENT = 
			"from COMMENT_LIKE where cmmt_id = :commentId";
	
	private static final String FIND_N_LIKES_BY_CMT = 
			"select count(cmmt_id) from COMMENT_LIKE where cmmt_id = :commentId";
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<CommentLike> findLikedBy(Integer userid) throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_WHO_LIKES);
		query.setParameter("userId", userid);
		List<CommentLike> likeList = query.list();
		return likeList;
	}

	@Override
	public List<CommentLike> findCommentLikebyCmtId(Integer id)
			throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_BY_COMMENT);
		query.setParameter("commentId", id);
		List<CommentLike> likeList = query.list();
		return likeList;
	}

	@Override
	public Integer addCommentLike(CommentLike commentLike) {
		CommentLike savedComment = (CommentLike) this.sessionFactory.getCurrentSession().save(commentLike);
		return savedComment.getlikeId();
	}

	@Override
	public Integer nLikeByCmtId(Integer id) throws LikeNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_N_LIKES_BY_CMT);
		query.setParameter("commentId", id);
		List<Integer> likeList = query.list();
		return likeList.get(0);
	}

}
