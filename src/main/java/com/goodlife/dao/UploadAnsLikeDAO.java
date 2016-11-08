package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.UploadAnsLike;

public interface UploadAnsLikeDAO {
	
	public Integer addUploadAnsLike(UploadAnsLike uploadAnsLike);	
	public List<UploadAnsLike> findLikedBy(Integer userid) throws LikeNotFoundException;
	public List<UploadAnsLike> findUploadAnsLikebyAnsId(Integer id) throws LikeNotFoundException;
	public Integer nLikeByAnsId(Integer id) throws LikeNotFoundException;
}