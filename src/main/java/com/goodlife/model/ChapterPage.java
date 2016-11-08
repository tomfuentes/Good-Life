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
@Table(name = "CHAPTER_PAGE", catalog = "goodlife")
public class ChapterPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "page_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer pageId;
	
	@JoinColumn(name = "chap_id", nullable = false)
	private Integer chapId;
	
	@Column(name = "page_num")
	private Integer pageNum;
	
	@Column(name = "page_url")
	private String pageUrl;
	
	@Column(name = "chap_page_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date chapPageTS;
	
	@Column(name = "published", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean published;

	public ChapterPage() {
		super();
	}

	public ChapterPage(Integer pageId, Integer chapId, Integer pageNum,
			String pageUrl, Date chapPageTS, Boolean published) {
		super();
		this.pageId = pageId;
		this.chapId = chapId;
		this.pageNum = pageNum;
		this.pageUrl = pageUrl;
		this.chapPageTS = chapPageTS;
		this.published = published;
	}

	public Date getChapPageTS() {
		return chapPageTS;
	}

	public void setChapPageTS(Date chapPageTS) {
		this.chapPageTS = chapPageTS;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getChapId() {
		return chapId;
	}

	public void setChapId(Integer chapId) {
		this.chapId = chapId;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
	
}
