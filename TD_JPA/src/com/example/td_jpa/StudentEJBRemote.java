package com.example.td_jpa;


import java.util.List;

import javax.ejb.Remote;


//Interface used by the class StudentEJB

@Remote
public interface StudentEJBRemote {
	
	List<Student> findStudents()  throws Exception ;
	Student findStudentById(long id)  throws Exception ;
	Student createStudent(Student s)  throws Exception ;
	void deleteStudent(Student s)  throws Exception;
	Student updateStudent(Student s)  throws Exception ;
	
}
