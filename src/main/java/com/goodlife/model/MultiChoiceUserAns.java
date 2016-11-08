package com.goodlife.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table (name = "MC_USER_ANS", catalog = "goodlife")
public class MultiChoiceUserAns implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4145302244569145574L;

	@Id
	@JoinColumn(name = "usr_id", nullable = false)
	private Integer userId;
	
	@Id
	@JoinColumn(name = "mc_q_id", nullable = false)
	private Integer multiQuesId;
	
	@Column(name = "usr_ans")
	private Integer userAnswer;
	
	@Column(name = "multi_choice_ans_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date multiChoiceAnsTS;

	public MultiChoiceUserAns() {
		super();
	}

	public MultiChoiceUserAns(Integer userId, Integer multiQuesId,
			Integer userAnswer, Date multiChoiceAnsTS) {
		super();
		this.userId = userId;
		this.multiQuesId = multiQuesId;
		this.userAnswer = userAnswer;
		this.multiChoiceAnsTS = multiChoiceAnsTS;
	}

	public Date getMultiChoiceAnsTS() {
		return multiChoiceAnsTS;
	}

	public void setMultiChoiceAnsTS(Date multiChoiceAnsTS) {
		this.multiChoiceAnsTS = multiChoiceAnsTS;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMultiQuesId() {
		return multiQuesId;
	}

	public void setMultiQuesId(Integer multiQuesId) {
		this.multiQuesId = multiQuesId;
	}

	public Integer getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(Integer userAnswer) {
		this.userAnswer = userAnswer;
	}

}
