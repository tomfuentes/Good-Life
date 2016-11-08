package com.goodlife.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity (name = "Student")
@Table(name="STUDENT", catalog = "goodlife")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@JoinColumn(name = "usr_id", unique = true, nullable = false)
	private Integer userId;
	
	@Column(name = "instructor_id")
	private Integer instructorId;
	
	@JoinColumn(name = "crnt_chap_id")
	private Integer currentChapterId;
	
	@Column(name = "start_dt")
	private Date startDate;
	
	@Column(name = "student_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date studentTS;


	public Student() {
		super();
	}
	
	public Student(Integer instructorId, Integer currentChapterId,
			Date startDate, Date promotionDate, Date studentTS) {
		super();
		this.instructorId = instructorId;
		this.currentChapterId = currentChapterId;
		this.startDate = startDate;
		this.studentTS = studentTS;
	}

	public Date getStudentTS() {
		return studentTS;
	}

	public void setStudentTS(Date studentTS) {
		this.studentTS = studentTS;
	}

	public Integer getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}

	public Integer getCurrentChapterId() {
		return currentChapterId;
	}

	public void setCurrentChapterId(Integer currentSubChapterId) {
		this.currentChapterId = currentSubChapterId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}