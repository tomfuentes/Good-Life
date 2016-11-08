package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.CommentFlagDAO;
import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.CommentFlag;

@Repository
public class CommentFlagDAOImpl implements CommentFlagDAO {
	
	private static final String FIND_WHO_FLAGGED = 
			"from COMMENT_FLAG where flgd_by = :userid";
	
	private static final String FIND_BY_COMMENT = 
			"from COMMENT_FLAG where cmmt_id = :commentId";
	
	private static final String FIND_N_FLAGS_BY_CMT = 
			"select count(cmmt_id) from COMMENT_FLAG where cmmt_id = :commentId";
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<CommentFlag> findFlaggedBy(Integer userid)
			throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_WHO_FLAGGED);
		query.setParameter("userid", userid);
		List<CommentFlag> flagList = query.list();
		return flagList;
	}

	@Override
	public List<CommentFlag> findCommentFlagbyCmtId(Integer id)
			throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_BY_COMMENT);
		query.setParameter("commentId", id);
		List<CommentFlag> flagList = query.list();
		return flagList;
	}

	@Override
	public Integer addCommentFlag(CommentFlag commentFlag) {
		CommentFlag savedFlag = (CommentFlag) this.sessionFactory.getCurrentSession().save(commentFlag);
		return savedFlag.getFlagId();
	}

	@Override
	public Integer nFlagByCmtId(Integer id) throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_N_FLAGS_BY_CMT);
		query.setParameter("commentId", id);
		List<Integer> flagList = query.list();
		return flagList.get(0);
	}

}
