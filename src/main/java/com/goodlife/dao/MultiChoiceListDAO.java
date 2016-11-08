package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceList;

public interface MultiChoiceListDAO {
	
	public Integer addMultiChoiceList(MultiChoiceList multiChoiceList);
	public Boolean deleteMultiChoiceList(Integer multiChoiceListId) ;
	public Boolean updateOrder(List<Integer> multiChoiceListIdArray) ;
	public Boolean updateTitle(Integer multiChoiceListId, String title) ;
	public Boolean updateDescription(Integer multiChoiceListId, String description) ;
	public List<MultiChoiceList> getAllMultiChoiceLists(Integer subChapId);
	public MultiChoiceList getMultiChoiceListById(Integer multiChoiceListId) throws MultipleChoiceNotFoundException;
	public Boolean setPublishMultiChoiceQ(Integer multiChoiceListId, Boolean published);
	public List<MultiChoiceList> getAllPublishedMultiChoiceLists(Integer subChapId);
	public Boolean setGradedMultiChoiceQ(Integer multiChoiceListId, Boolean graded);
}
