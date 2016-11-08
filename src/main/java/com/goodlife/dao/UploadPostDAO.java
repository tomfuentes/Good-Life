package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.UploadPost;


public interface UploadPostDAO {

	public Integer addUploadPost(UploadPost uploadPost) throws UserNotFoundException;
	public void deleteUploadPost(Integer postId) throws UserNotFoundException;
	public List<UploadPost> getUploadPostByUser(Integer userId) throws UserNotFoundException;
	public List<UploadPost> getRecentPosts() throws UserNotFoundException;
	
}
