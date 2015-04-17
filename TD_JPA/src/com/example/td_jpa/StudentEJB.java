package com.example.td_jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class StudentEJB implements StudentEJBRemote {

	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TD_JPA");
    EntityManager em = emf.createEntityManager() ;
    
	public StudentEJB() {
	}
	
	
	public Student findStudentById(long id)  throws Exception {
		return em.find(Student.class, id);
	}

	
	public Student createStudent(Student s)  throws Exception {
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit() ;
		return s;
	}

	
	public void deleteStudent(Student s)  throws Exception {
		em.getTransaction().begin();
		em.remove(s);
		em.getTransaction().commit() ;
	}

	
	public Student updateStudent(Student s)  throws Exception  {
		em.getTransaction().begin();
		em.merge(s);
		em.getTransaction().commit() ;
		return s;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Student> findStudents()  throws Exception {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT s FROM Student as s");
		em.getTransaction().commit() ;
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findProjects(long id)  throws Exception {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT p FROM Project p WHERE p.owner_id =:id");
		query.setParameter("id", id);
		em.getTransaction().commit() ;
		return query.getResultList();
	}
	
	
	
}
