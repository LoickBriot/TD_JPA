package com.example.td_jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/* 
 Implementation class of Enterprise Java Bean class : StudentEJB
 */

public class StudentEJB implements StudentEJBRemote {

	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TD_JPA");
    EntityManager em = emf.createEntityManager() ;
    
	public StudentEJB() {
	}
	
	// Find a student in database thanks to his ID
	public Student findStudentById(long id)  throws Exception {
		return em.find(Student.class, id);
	}

	// Create a student in database with in input an object of type Student
	public Student createStudent(Student s)  throws Exception {
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit() ;
		return s;
	}

	// Delete a student in database with in input an object of type Student 
	public void deleteStudent(Student s)  throws Exception {
		em.getTransaction().begin();
		em.remove(s);
		em.getTransaction().commit() ;
	}

	// Update the fields of a student saved in database 
	public Student updateStudent(Student s)  throws Exception  {
		em.getTransaction().begin();
		em.merge(s);
		em.getTransaction().commit() ;
		return s;
	}
	
	
	// Find all students saved in database and return a List which contains all students
	@SuppressWarnings("unchecked")
	public List<Student> findStudents()  throws Exception {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT s FROM Student as s");
		em.getTransaction().commit() ;
		return query.getResultList();
	}
	
	// Find all projects of a student saved in database and return a List which contains all these projects. This method takes in input the student's ID. 
	@SuppressWarnings("unchecked")
	public List<Project> findProjects(long id)  throws Exception {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT p FROM Project p WHERE p.owner_id =:id");
		query.setParameter("id", id);
		em.getTransaction().commit() ;
		return query.getResultList();
	}
	
	public Project createProject(Student s, String title) throws Exception {
		Project p = new Project();
		ProjectEJB e_p = new ProjectEJB();
		p.setTitle(title);
		p.setOwner(s.getStudentID());
		e_p.createProject(p);
		return p;
		
	}
	
	
	
}
