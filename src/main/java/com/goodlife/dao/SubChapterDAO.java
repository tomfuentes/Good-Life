package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.SubChapterNotFoundException;
import com.goodlife.model.SubChapter;

public interface SubChapterDAO {
	
	public Integer addSubChapter(SubChapter subChapter);
	public Boolean deleteSubChapter(Integer subChapId);
	public Boolean updateOrder(List<Integer> subChapterIdList);
	public Boolean updateSubChapter(SubChapter updatedSubChapter);
	public Boolean updateTitle(Integer subChapId, String subChapTitle);
	public Boolean updateDescription(Integer subChapId, String subChapDescr);
	public List<SubChapter> getSubChapListByChapter(Integer chapId);
	public SubChapter getSubChapterById(Integer subChapId) throws SubChapterNotFoundException;
	public Boolean setPublishSubChapter(Integer subChapId, Boolean published);
	public List<SubChapter> getPublishedSubChapListByChap(Integer chapId);
}
