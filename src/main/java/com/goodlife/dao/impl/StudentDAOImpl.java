package com.goodlife.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.StudentDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.Student;
import com.goodlife.model.Users;

@Repository
public class StudentDAOImpl implements StudentDAO  {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private UsersDAO userDAO;

	@Override
	public Student findStudentByUserId(Integer userId) throws ObjectNotFoundException{
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Student.class);
		criteria.add(Restrictions.eqOrIsNull("userId", userId));
		Student student = (Student) criteria.uniqueResult();
		if(student == null)
			throw new ObjectNotFoundException(null,"Student with userId: " + userId + " not found.");
		return student;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findStudentByInstructorId(Integer instructorId)
			throws ObjectNotFoundException{
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Student.class);
		criteria.add(Restrictions.eqOrIsNull("instructorId", instructorId));
		List<Student> studentList = criteria.list();
		if(studentList == null)
			return new ArrayList<Student>();
		else
			return studentList;
	}

	@SuppressWarnings("unused")
	@Override
	public Boolean addStudent(Student user){
		Boolean isSuccess = Boolean.TRUE;
		try {
			Users student = userDAO.findByUserId(user.getUserId());
			this.sessionFactory.getCurrentSession().save(user);
		} catch (UserNotFoundException e) {
			isSuccess = Boolean.FALSE;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public Boolean deleteStudent(Integer userId){
		try{
			Student student = findStudentByUserId(userId);
			this.sessionFactory.getCurrentSession().delete(student);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean addExistingStudentToRoster(Integer userId, Integer instructorId){
		try{
			Student student = findStudentByUserId(userId);
			student.setInstructorId(instructorId);
			this.sessionFactory.getCurrentSession().saveOrUpdate(student);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> getAllowedChapters(Integer userId){
		
		Student student = findStudentByUserId(userId);
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Chapter.class);
		criteria.add(Restrictions.and(Restrictions.le("chapId", student.getCurrentChapterId()),Restrictions.eq("published", true)));
		List<Chapter> chapterList = criteria.list();
		if(chapterList == null)
			return new ArrayList<Chapter>();
		else
			return chapterList;
	}
	
	@Override
	public Boolean updateCurrentChapter(Integer userId, Integer chapId) {
		
		try{
			Student student = findStudentByUserId(userId);
			student.setCurrentChapterId(chapId);
			this.sessionFactory.getCurrentSession().saveOrUpdate(student);
			return Boolean.TRUE;
		}catch(ObjectNotFoundException e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
}
