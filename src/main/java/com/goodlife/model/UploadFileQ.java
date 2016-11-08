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
@Table(name = "UPLOAD_FILE_Q", catalog = "goodlife")
public class UploadFileQ implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "up_q_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer uploadQuesId;
	
	@JoinColumn(name = "sub_chap_id", nullable = false)
	private Integer subChapId;
	
	@Column(name = "help_txt")
	private String helpText;
	
	@Column(name = "descr", columnDefinition = "BLOB")
	private String description;
	
	@Column(name = "published", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean published;
	
	@Column(name = "upload_file_q_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date uploadFileQTS;

	public UploadFileQ() {
		super();
	}

	public UploadFileQ(Integer uploadQuesId, Integer subChapId,
			String helpText, String description, Boolean published, Date uploadFileQTS) {
		super();
		this.uploadQuesId = uploadQuesId;
		this.subChapId = subChapId;
		this.helpText = helpText;
		this.description = description;
		this.published = published;
		this.uploadFileQTS = uploadFileQTS;
	}

	
	public Date getUploadFileQTS() {
		return uploadFileQTS;
	}

	public void setUploadFileQTS(Date uploadFileQTS) {
		this.uploadFileQTS = uploadFileQTS;
	}

	public Integer getUploadQuesId() {
		return uploadQuesId;
	}

	public void setUploadQuesId(Integer uploadQuesId) {
		this.uploadQuesId = uploadQuesId;
	}

	public Integer getSubChapId() {
		return subChapId;
	}

	public void setSubChapId(Integer subChapId) {
		this.subChapId = subChapId;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
}
