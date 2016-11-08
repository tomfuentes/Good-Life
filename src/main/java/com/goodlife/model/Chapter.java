package com.goodlife.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CHAPTER", catalog = "goodlife")
public class Chapter implements Serializable{
	
	private static final long serialVersionUID = 307308746494710816L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chap_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer chapId;
	
	@Column(name = "chap_descr")
	private String chapDescr;
	
	@Column(name = "chap_title", nullable = false)
	private String chapTitle;
	
	@Column(name = "order_id", nullable = false)
	private Integer orderId;
	
	@Column(name = "chap_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date chapTS;
	
	@Column(name = "published", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean published;

	public Chapter() {
		super();
	}

	public Chapter(Integer chapId, String chapDescr, String chapTitle,
			Integer orderId, Date chapTS, Boolean published) {
		super();
		this.chapId = chapId;
		this.chapDescr = chapDescr;
		this.chapTitle = chapTitle;
		this.orderId = orderId;
		this.chapTS = chapTS;
		this.published = published;
	}

	public Date getChapTS() {
		return chapTS;
	}

	public void setChapTS(Date chapTS) {
		this.chapTS = chapTS;
	}

	public Integer getChapId() {
		return chapId;
	}

	public void setChapId(Integer chapId) {
		this.chapId = chapId;
	}

	public String getChapDescr() {
		return chapDescr;
	}

	public void setChapDescr(String chapDescr) {
		this.chapDescr = chapDescr;
	}

	public String getChapTitle() {
		return chapTitle;
	}

	public void setChapTitle(String chapTitle) {
		this.chapTitle = chapTitle;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	
}
