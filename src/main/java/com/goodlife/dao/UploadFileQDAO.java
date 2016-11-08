
package com.goodlife.dao;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.model.UploadFileQ;

public interface UploadFileQDAO {

	public Integer addUploadFileQuestion(UploadFileQ uploadFileQ);
	public Boolean deleteUploadFileQuestion(Integer uploadQuesId);
	public Boolean updateDescription(Integer uploadQuesId, String description);
	public Boolean updateHelpText(Integer uploadQuesId, String helpText);
	public UploadFileQ getUploadFileQuestion(Integer uploadFileQId) throws ObjectNotFoundException;
	public UploadFileQ getUploadFileQBySubchapId(Integer subChapId) throws ObjectNotFoundException;
	public Boolean setPublishedUploadFileQ(Integer quesId, Boolean published);
	public UploadFileQ getPublishedUploadFileQBySubchapId(Integer subChapId);
}
