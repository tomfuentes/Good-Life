package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceQ;

public interface MultiChoiceQDAO {
	
	public Integer addMultiChoice(MultiChoiceQ multiChoiceQ);
	public Boolean deleteMultiChoice(Integer multiChoiceId) ;
	public Boolean updateOrder(List<Integer> multiChoiceIdList) ;
	public Boolean updateQuestionText(Integer multiChoiceId, String quesText) ;
	public Boolean updateHelpText(Integer multiChoiceId, String helpText) ;
	public Boolean updateCorrectAnswer(Integer multiChoiceId, Integer correctAnswer) ;
	public List<MultiChoiceQ> getAllMultiChoice(Integer multiChoiceListId);
	public MultiChoiceQ getMultiChoiceQById(Integer multiChoiceId) throws MultipleChoiceNotFoundException;
	public Boolean setPublishMultiChoiceQ(Integer multiChoiceId, Boolean published);
	public List<MultiChoiceQ> getAllPublishedMultiChoice(Integer multiChoiceListId);
	public Boolean deleteAllMultiChoiceByList(Integer multiChoiceListId);
}
