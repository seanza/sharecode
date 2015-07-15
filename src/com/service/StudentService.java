package com.service;

import java.util.List;
import com.model.Student;

public interface StudentService {
	public List<Student> findName(Student student);
	public int create(Student student);
}
