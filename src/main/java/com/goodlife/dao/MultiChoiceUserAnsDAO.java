package com.goodlife.dao;

import com.goodlife.model.MultiChoiceUserAns;

public interface MultiChoiceUserAnsDAO {
	
	public Boolean addMultiChoiceAnswer(MultiChoiceUserAns multiChoiceAns);
	public Integer getUserAnswer(Integer userId, Integer multiQuesId);
	public MultiChoiceUserAns getUserAnswerObj(Integer userId, Integer multiQuesId);
	public Boolean isMultiChoiceCorrect(Integer userId, Integer multiQuesId);
	public Boolean isMultiChoiceListComplete(Integer userId, Integer multiChoiceListId);
	public Boolean isMultiChoiceSubChapComplete(Integer userId, Integer subChapId);
}
