package com.goodlife.dao;

import com.goodlife.model.UploadedAnswer;

public interface UploadedAnswerDAO {

	public Integer addUploadedAnswer(UploadedAnswer uploadedAnswer);
	public Boolean setApproveAnswer(Integer uploadAnswerId, Boolean aprvd);
	public Boolean setSharedAnswer(Integer uploadAnswerId, Boolean shared);
	public UploadedAnswer getUserAnswer(Integer userId, Integer uploadQuesId);
	public UploadedAnswer getUserAnswerByQuesId(Integer uploadAnswerId);
	public Boolean isUploadedQuestionComplete(Integer userId, Integer subChapId);
}
