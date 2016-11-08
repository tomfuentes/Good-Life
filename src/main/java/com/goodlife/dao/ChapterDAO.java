package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.model.Chapter;

public interface ChapterDAO {

	public Integer addChapter(Chapter chapter);
	public Boolean deleteChapter(Integer chapterId);
	public Chapter findByChapterId(Integer chapterId) throws ChapterNotFoundException;
	public Boolean updateChapter(Chapter updatedChapter);
	public Boolean updateOrder(List<Integer> chapterList);
	public Boolean updateTitle(Integer chapterId, String newChapterTitle);
	public Boolean updateDescr(Integer chapterId, String newChapterDescr);
	public List<Chapter> listAllChapters();
	public List<Chapter> listAllPublishedChapters();
	public List<Chapter> listAllChapterDrafts();
	public Boolean updatePublished(Integer chapterId, Boolean published);
	
}
