package com.goodlife.dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.model.ShortAnswerUserAnswer;

public interface ShortAnswerUserAnswerDAO {

	public Boolean addUserAnswer(ShortAnswerUserAnswer shortAnswerUA);
	public ShortAnswerUserAnswer getUserAnswer(Integer userId, Integer saQId) throws ObjectNotFoundException;
	public Boolean approveAnswer(Integer userId, Integer saQId);
	public Boolean isShortAnswerSubChapComplete(Integer userId, Integer subChapId);
	public List<ShortAnswerUserAnswer> listAllUserShortAnsBySubChap(Integer userId, Integer subChapId);
	public List<ShortAnswerUserAnswer> listSubmittedShortAnsBySubChap(Integer userId, Integer subChapId);
}
