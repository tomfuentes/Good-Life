package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.UploadAnsFlagDAO;
import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.model.UploadAnsFlag;

@Repository
public class UploadAnsFlagDAOImpl implements UploadAnsFlagDAO {
	
	private static final String FIND_WHO_FLAGGED = "from UPLOAD_ANS_FLAG where flgd_by = :userid";
	
	private static final String FIND_BY_ANS = "from UPLOAD_ANS_FLAG where ans_id = :ansId";
	
	private static final String FIND_N_FLAGS_BY_ANS = 
			"select count(ans_id) from UPLOAD_ANS_FLAG where ans_id = :ansId";
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<UploadAnsFlag> findFlaggedBy(Integer userid)
			throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_WHO_FLAGGED);
		query.setParameter("userid", userid);
		List<UploadAnsFlag> flagList = query.list();
		return flagList;
	}

	@Override
	public List<UploadAnsFlag> findUploadAnsFlagbyAnsId(Integer id)
			throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_BY_ANS);
		query.setParameter("ansId", id);
		List<UploadAnsFlag> flagList = query.list();
		return flagList;
	}

	@Override
	public Integer addUploadAnsFlag(UploadAnsFlag uploadAnsFlag) {
		UploadAnsFlag savedFlag = (UploadAnsFlag) this.sessionFactory.getCurrentSession().save(uploadAnsFlag);
		return savedFlag.getFlagId();
	}

	public Integer nFlagByAnsId(Integer id) throws FlagNotFoundException {
		Query query = this.sessionFactory.getCurrentSession().createQuery(FIND_N_FLAGS_BY_ANS);
		query.setParameter("ansId", id);
		List<Integer> flagList = query.list();
		return flagList.get(0);
	}

}
