package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.ShortAnswerNotFoundException;
import com.goodlife.model.ShortAnswerQ;

public interface ShortAnswerQDAO {

	public Integer addShortAnswerQuestion(ShortAnswerQ shortAnswerQ);
	public Boolean updateQuestionText(Integer saQId, String question) ;
	public Boolean updateHelpText(Integer saQId, String helpText) ;
	public Boolean updateOrderId(List<Integer> saQIdList) ;
	public ShortAnswerQ getShortAnswerById(Integer saQId) throws ShortAnswerNotFoundException;
	public List<ShortAnswerQ> getShortAnswerBySubChapter(Integer subChapId);
	public Boolean setPublishShortAnswer(Integer saQId, Boolean published);
	public List<ShortAnswerQ> getPublishedShortAnswerBySubChapter(Integer subChapId);
}
