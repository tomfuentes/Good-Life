package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.LikeNotFoundException;
import com.goodlife.model.UploadPostLike;

public interface UploadPostLikeDAO {
	
	public Integer addUploadPostLike(UploadPostLike uploadPostLike);	
	public List<UploadPostLike> findLikedBy(Integer userid) throws LikeNotFoundException;
	public List<UploadPostLike> findUploadPostLikebyPostId(Integer id) throws LikeNotFoundException;
	public Integer nLikeByPostId(Integer id) throws LikeNotFoundException;
}