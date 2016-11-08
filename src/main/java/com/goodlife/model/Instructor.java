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

@Entity (name = "Instructor")
@Table(name="INSTRUCTOR", catalog = "goodlife")
public class Instructor implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@JoinColumn(name = "usr_id", unique = true, nullable = false)
	private Integer userId;
	
	@Column(name = "n_stdnt")
	private Integer numStudent;
	
	@Column(name = "tot_cap")
	private Integer totalCapacity;
	
	@Column(name = "start_dt")
	private Date startDate;
	
	@Column(name = "instructor_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date instructorTS;

	
	public Instructor() {
		super();
	}

	public Instructor(Integer userId, Integer numStudent,
			Integer totalCapacity, Date startDate, Date instructorTS) {
		super();
		this.userId = userId;
		this.numStudent = numStudent;
		this.totalCapacity = totalCapacity;
		this.startDate = startDate;
		this.instructorTS = instructorTS;
	}

	public Date getInstructorTS() {
		return instructorTS;
	}

	public void setInstructorTS(Date instructorTS) {
		this.instructorTS = instructorTS;
	}

	public Integer getNumStudent() {
		return numStudent;
	}

	public void setNumStudent(Integer numStudent) {
		this.numStudent = numStudent;
	}

	public Integer getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(Integer totalCapacity) {
		this.totalCapacity = totalCapacity;
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