package com.goodlife.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity (name = "SuperAdmin")
@Table(name="SUPER_ADMIN", catalog = "goodlife")
public class SuperAdmin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@JoinColumn(name = "usr_id", unique = true, nullable = false)
	private Integer userId;
	
	@Column(name = "admin_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date adminTS;
	
	public SuperAdmin() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAdminTS() {
		return adminTS;
	}

	public void setAdminTS(Date adminTS) {
		this.adminTS = adminTS;
	}
	
	
	
}