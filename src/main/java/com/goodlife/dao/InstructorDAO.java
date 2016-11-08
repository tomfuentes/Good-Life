package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;
import com.goodlife.model.Student;

public interface InstructorDAO {
	
	public Instructor findInstructorByUserId(Integer userId) throws UserNotFoundException;
	public List<Student> findStudentsByInstructorId(Integer instructorId);
	public Double getStudentProgress(Integer userId);
	public Boolean addInstructor(Integer userId);
	public Boolean addStudentToRoster(Integer userId, Integer instructorId);
	public Boolean changeRosterCapSize(Integer instructorId, Integer rosterSize);
	public Boolean removeStudentFromRoster(Integer userId, Integer instructorId);
}