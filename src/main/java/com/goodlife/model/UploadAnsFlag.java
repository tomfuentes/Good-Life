package com.goodlife.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity (name = "UploadAnsFlag")
@Table(name="UPLOAD_ANS_FLAG", catalog = "goodlife")
public class UploadAnsFlag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flg_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer flagId;

	@Column(name = "flgd_by")
	private Integer flaggedBy;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "up_ans_id", nullable = false)
	//@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Integer uploadAnswerId;

	public Integer getFlagId() {
		return flagId;
	}

	public void setFlagId(Integer flagId) {
		this.flagId = flagId;
	}

	public Integer getFlaggedBy() {
		return flaggedBy;
	}

	public void setFlaggedBy(Integer flaggedBy) {
		this.flaggedBy = flaggedBy;
	}

	public Integer getUpAnsId() {
		return uploadAnswerId;
	}

	public void setUpAnsId(Integer upAnsId) {
		this.uploadAnswerId = upAnsId;
	}
	
}
