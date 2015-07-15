package com.service.impl;

import java.util.List;
import com.dao.StudentDao;
import com.model.Student;
import com.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private StudentDao studentDao;

	public StudentServiceImpl() {
		studentDao = new StudentDao();
	}

	public List<Student> findName(Student student) {
		return studentDao.findName(student);
	}

	public int create(Student student) {
		return studentDao.create(student);
	}
}
