package com.goodlife.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.InstructorDAO;
import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.dao.ShortAnswerUserAnswerDAO;
import com.goodlife.dao.StudentDAO;
import com.goodlife.dao.SubChapterDAO;
import com.goodlife.dao.UploadedAnswerDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.Instructor;
import com.goodlife.model.Student;
import com.goodlife.model.SubChapter;

@Repository
public class InstructorDAOImpl implements InstructorDAO  {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private ChapterDAO chapterDAO;
	
	@Autowired
	private SubChapterDAO subChapDAO;
	
	@Autowired
	private MultiChoiceUserAnsDAO multiAnsDAO;
	
	@Autowired
	private ShortAnswerUserAnswerDAO shortAnsUADAO;
	
	@Autowired
	private UploadedAnswerDAO uploadAnsDAO;
	
	@Override
	public Instructor findInstructorByUserId(Integer userId) throws UserNotFoundException {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Instructor.class);
		criteria.add(Restrictions.eqOrIsNull("userId", userId));
		Instructor instructor = (Instructor) criteria.uniqueResult();
		if (null == instructor) {
        	throw new UserNotFoundException("Instructor: " + userId + ".  Not found in the database!");
        }
		return instructor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findStudentsByInstructorId(Integer instructorId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Student.class);
		criteria.add(Restrictions.eqOrIsNull("instructorId", instructorId));
		List<Student> studentList = criteria.list();
		if(studentList == null)
			return new ArrayList<Student>();
		else
			return studentList;
	}
	
	@Override
	public Double getStudentProgress(Integer userId){
		
		List<Chapter> chapList = chapterDAO.listAllPublishedChapters();
		Integer currChap = studentDAO.findStudentByUserId(userId).getCurrentChapterId();
		List<SubChapter> subChapList;
		Double totalSubChaps = 0.0;
		Double completeSubChaps = 0.0;
		Integer subChapId = 0;
		for(int i = 0; i < chapList.size(); i++){
			
			subChapList = subChapDAO.getPublishedSubChapListByChap(chapList.get(i).getChapId());
			totalSubChaps += subChapList.size();
			
			if(currChap == chapList.get(i).getChapId())
				for(int j = 0; j < subChapList.size(); j++){
					subChapId = subChapList.get(j).getSubChapId();
					if(multiAnsDAO.isMultiChoiceSubChapComplete(userId,subChapId) ||
					   shortAnsUADAO.isShortAnswerSubChapComplete(userId, subChapId) ||
					   uploadAnsDAO.isUploadedQuestionComplete(userId, subChapId))
						completeSubChaps += 1;
				}
			else if(currChap > chapList.get(i).getOrderId())
				completeSubChaps += subChapList.size();
			
		}
		System.out.println(completeSubChaps + " / " + totalSubChaps);
		
		return completeSubChaps / totalSubChaps;
	}

	@Override
	public Boolean addInstructor(Integer userId) {
		Instructor instructor = new Instructor();
		try {
			usersDAO.findByUserId(userId);
			instructor.setStartDate(new Date());
			instructor.setUserId(userId);
			instructor.setNumStudent(0);
			instructor.setTotalCapacity(0);
			this.sessionFactory.getCurrentSession().saveOrUpdate(instructor);
			return Boolean.TRUE;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean addStudentToRoster(Integer userId, Integer instructorId){
		
		Student student;
		try {
			Instructor instructor = findInstructorByUserId(instructorId);
			if(instructor.getTotalCapacity() <= instructor.getNumStudent())
				return Boolean.FALSE;
			else{
				try{
					student = studentDAO.findStudentByUserId(userId);
					if(student == null)
						return Boolean.FALSE;
					else{
						student.setInstructorId(instructorId);
						this.sessionFactory.getCurrentSession().saveOrUpdate(student);
						instructor.setNumStudent(instructor.getNumStudent() + 1);
						this.sessionFactory.getCurrentSession().saveOrUpdate(instructor);
						return Boolean.TRUE;
					}
				}catch(ObjectNotFoundException e){
					e.printStackTrace();
					return Boolean.FALSE;
				}
			}
		} catch (UserNotFoundException e1) {
			e1.printStackTrace();
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean removeStudentFromRoster(Integer userId, Integer instructorId){
		
		try {
			Instructor instructor = findInstructorByUserId(instructorId);
			try{
				Student student = studentDAO.findStudentByUserId(userId);
				student.setInstructorId(null);
				instructor.setNumStudent(instructor.getNumStudent() - 1);
				this.sessionFactory.getCurrentSession().saveOrUpdate(student);
				this.sessionFactory.getCurrentSession().saveOrUpdate(instructor);
				return Boolean.TRUE;
			}catch(ObjectNotFoundException e){
				e.printStackTrace();
				return Boolean.FALSE;
			}
			
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	
	@Override
	public Boolean changeRosterCapSize(Integer instructorId, Integer rosterSize){
		
		try {
			Instructor instructor = findInstructorByUserId(instructorId);
			if(instructor.getNumStudent() > rosterSize)
				return Boolean.FALSE;
			else{
				instructor.setTotalCapacity(rosterSize);
				this.sessionFactory.getCurrentSession().saveOrUpdate(instructor);
				return Boolean.TRUE;
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
}
