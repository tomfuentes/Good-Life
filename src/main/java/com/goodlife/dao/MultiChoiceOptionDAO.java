package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.MultipleChoiceOptionNotFoundException;
import com.goodlife.model.MultiChoiceOption;

public interface MultiChoiceOptionDAO {
	
	public Integer addMultiChoiceOption(MultiChoiceOption multiChoiceOption);
	public Boolean updateChoiceText(Integer optionId, String text) ;
	public List<MultiChoiceOption> getMultiChoiceOptions(Integer multiQuesId) ;
	public Boolean deleteMultiChoiceOption(Integer optionId) ;
	public MultiChoiceOption findMultiChoiceOptionById(Integer optionId) throws MultipleChoiceOptionNotFoundException;
	public Boolean setPublishMulitChoiceOption(Integer optionId, Boolean published);
	public List<MultiChoiceOption> getPublishedMultiChoiceOptions(Integer multiQuesId);
}
