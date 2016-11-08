package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.model.UploadPostFlag;

public interface UploadPostFlagDAO {
	
	public Integer addUploadPostFlag(UploadPostFlag uploadPostFlag);	
	public List<UploadPostFlag> findFlaggedBy(Integer userid) throws FlagNotFoundException;
	public List<UploadPostFlag> findUploadPostFlagbyPostId(Integer id) throws FlagNotFoundException;
	public Integer nFlagByPostId(Integer id) throws FlagNotFoundException;
}