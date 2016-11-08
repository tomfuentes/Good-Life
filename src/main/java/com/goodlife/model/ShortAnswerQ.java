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
@Table(name = "SHORT_ANS_Q", catalog = "goodlife")
public class ShortAnswerQ implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sa_q_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer saQId;
	
	@JoinColumn(name = "sub_chap_id", nullable = false)
	private Integer subChapId;
	
	@Column(name = "question", columnDefinition = "BLOB")
	private String question;
	
	@Column(name = "help_txt")
	private String helpText;
	
	@Column(name = "order_id", nullable = false)
	private Integer orderId;
	
	@Column(name = "published", columnDefinition = "TINYINT(1) DEFAULT 0")
	private Boolean published;
	
	@Column(name = "short_ans_q_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date shortAnsQTS;

	public ShortAnswerQ() {
		super();
	}

	public ShortAnswerQ(Integer saQId, Integer subChapId, String question,
			String helpText, Integer orderId, Boolean published, Date shortAnsQTS) {
		super();
		this.saQId = saQId;
		this.subChapId = subChapId;
		this.question = question;
		this.helpText = helpText;
		this.orderId = orderId;
		this.published = published;
		this.shortAnsQTS = shortAnsQTS;
	}

	public Date getShortAnsQTS() {
		return shortAnsQTS;
	}

	public void setShortAnsQTS(Date shortAnsQTS) {
		this.shortAnsQTS = shortAnsQTS;
	}

	public Integer getSaQId() {
		return saQId;
	}

	public void setSaQId(Integer saQId) {
		this.saQId = saQId;
	}

	public Integer getSubChapId() {
		return subChapId;
	}

	public void setSubChapId(Integer subChapId) {
		this.subChapId = subChapId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpTxt) {
		this.helpText = helpTxt;
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
