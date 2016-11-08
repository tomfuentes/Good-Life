package com.goodlife.model;

import java.io.Serializable;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "UPLOADED_ANS", catalog = "goodlife")
public class UploadedAnswer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "up_ans_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer uploadAnswerId;
	
	@Column(name = "media_typ_id")
	private Integer mediaTypeId;
	
	@Column(name = "file_path")
	private String filePath;
	
	@JoinColumn(name = "usr_id", nullable = false)
	private Integer userId;
	
	@Column(name = "aprvd", columnDefinition = "TINYINT", length = 1)
	private boolean aprvd;
	
	@Column(name = "shared", columnDefinition = "TINYINT", length = 1)
	private boolean shared;
	
	@JoinColumn(name = "up_q_id", nullable = false)
	private Integer uploadQuesId;
	
	@Column(name = "upload_ans_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date uploadAnsTS;

	public UploadedAnswer() {
		super();
	}

	public UploadedAnswer(Integer uploadAnswerId, Integer mediaTypeId,
			String filePath, Integer userId, boolean aprvd, boolean shared,
			Integer uploadQuesId, Date uploadAnsTS) {
		super();
		this.uploadAnswerId = uploadAnswerId;
		this.mediaTypeId = mediaTypeId;
		this.filePath = filePath;
		this.userId = userId;
		this.aprvd = aprvd;
		this.shared = shared;
		this.uploadQuesId = uploadQuesId;
		this.uploadAnsTS = uploadAnsTS;
	}

	public Date getUploadAnsTS() {
		return uploadAnsTS;
	}

	public void setUploadAnsTS(Date uploadAnsTS) {
		this.uploadAnsTS = uploadAnsTS;
	}

	public Integer getUploadAnswerId() {
		return uploadAnswerId;
	}

	public void setUploadAnswerId(Integer uploadAnswerId) {
		this.uploadAnswerId = uploadAnswerId;
	}

	public Integer getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isAprvd() {
		return aprvd;
	}

	public void setAprvd(boolean aprvd) {
		this.aprvd = aprvd;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public Integer getUploadQuesId() {
		return uploadQuesId;
	}

	public void setUploadQuesId(Integer uploadQuesId) {
		this.uploadQuesId = uploadQuesId;
	}	
}
