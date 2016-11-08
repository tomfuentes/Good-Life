package com.goodlife.dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.Student;

public interface StudentDAO {
	public Student findStudentByUserId(Integer userId) throws ObjectNotFoundException;
	public List<Student> findStudentByInstructorId(Integer instructorId) throws ObjectNotFoundException;
	public Boolean addStudent(Student user) throws UserNotFoundException;
	public Boolean deleteStudent(Integer userId) throws ObjectNotFoundException;
	public Boolean addExistingStudentToRoster(Integer userId, Integer instructorId) throws ObjectNotFoundException;
	public List<Chapter> getAllowedChapters(Integer userId) throws ObjectNotFoundException;
	public Boolean updateCurrentChapter(Integer userId, Integer chapId);
}