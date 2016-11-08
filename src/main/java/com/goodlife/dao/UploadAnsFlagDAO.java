package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.FlagNotFoundException;
import com.goodlife.model.UploadAnsFlag;

public interface UploadAnsFlagDAO {
	
	public Integer addUploadAnsFlag(UploadAnsFlag uploadAnsFlag);	
	public List<UploadAnsFlag> findFlaggedBy(Integer userid) throws FlagNotFoundException;
	public List<UploadAnsFlag> findUploadAnsFlagbyAnsId(Integer id) throws FlagNotFoundException;
	public Integer nFlagByAnsId(Integer id) throws FlagNotFoundException;
}