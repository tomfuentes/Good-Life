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
@Table (name = "MULTI_CHOICE_LIST", catalog = "goodlife")
public class MultiChoiceList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mc_list_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer multiChoiceListId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "descr")
	private String description;
	
	@JoinColumn(name = "sub_chap_id", nullable = false)
	private Integer subChapId;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "published", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean published;

	@Column(name = "graded", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean graded;
	
	@Column(name = "multi_choice_list_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date multiChoiceListTS;
	
	public MultiChoiceList() {
		super();
	}

	public MultiChoiceList(Integer multiChoiceListId, String title,
			String description, Integer subChapId, Integer orderId,
			Boolean published, Boolean graded, Date multiChoiceListTS) {
		super();
		this.multiChoiceListId = multiChoiceListId;
		this.title = title;
		this.description = description;
		this.subChapId = subChapId;
		this.orderId = orderId;
		this.published = published;
		this.graded = graded;
		this.multiChoiceListTS = multiChoiceListTS;
	}

	public Date getMultiChoiceListTS() {
		return multiChoiceListTS;
	}

	public void setMultiChoiceListTS(Date multiChoiceListTS) {
		this.multiChoiceListTS = multiChoiceListTS;
	}

	public Integer getMultiChoiceListId() {
		return multiChoiceListId;
	}

	public void setMultiChoiceListId(Integer multiChoiceListId) {
		this.multiChoiceListId = multiChoiceListId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSubChapId() {
		return subChapId;
	}

	public void setSubChapId(Integer subChapId) {
		this.subChapId = subChapId;
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

	public Boolean getGraded() {
		return graded;
	}

	public void setGraded(Boolean graded) {
		this.graded = graded;
	}
}