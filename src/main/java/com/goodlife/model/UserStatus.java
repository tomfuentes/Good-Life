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
@Table(name = "USER_STATUS", catalog = "goodlife")
public class UserStatus implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_sts_id", unique = true, nullable = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer userStatusId;
	
	@JoinColumn(name = "usr_id", unique = false, nullable = false)
	private Integer userId;
	
	
	@Column(name = "sts_typ_cd", unique = false, nullable = false)
	private char statusTypeCode;
	
	
	@Column(name = "strt_dt", unique = false, nullable = true)
	private Date startDate;
	
	
	@Column(name = "end_dt", unique = false, nullable = true)
	private Date endDate;
	
	@Column(name = "user_status_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date userStatusTS;
	
	public UserStatus(){
		
	}

	public UserStatus(Integer userStatusId, Integer userId, char statusTypeCode,
			Date startDate, Date endDate, Date userStatusTS) {
		super();
		this.userStatusId = userStatusId;
		this.userId = userId;
		this.statusTypeCode = statusTypeCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userStatusTS = userStatusTS;
	}
	
	

	public Date getUserStatusTS() {
		return userStatusTS;
	}

	public void setUserStatusTS(Date userStatusTS) {
		this.userStatusTS = userStatusTS;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserStatusId() {
		return userStatusId;
	}

	public void setUserStatusId(Integer userStatusId) {
		this.userStatusId = userStatusId;
	}

	public char getStatusTypeCode() {
		return statusTypeCode;
	}

	public void setStatusTypeCode(char statusTypeCode) {
		this.statusTypeCode = statusTypeCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
