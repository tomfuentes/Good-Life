package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.model.CommentFlag;

public interface CommentFlagDAO {
	
	public Integer addCommentFlag(CommentFlag commentFlag);	
	public List<CommentFlag> findFlaggedBy(Integer userid) throws FlagNotFoundException;
	public List<CommentFlag> findCommentFlagbyCmtId(Integer id) throws FlagNotFoundException;
	public Integer nFlagByCmtId(Integer id) throws FlagNotFoundException;
}