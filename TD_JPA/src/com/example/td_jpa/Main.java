package com.example.td_jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) throws Exception {		
		
		// this method enable to test some methods of my other classes without Vaadin

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TD_JPA") ;
		EntityManager em = emf.createEntityManager() ;
		Student student =  new Student() ;
		Student student2 =  new Student() ;
	    student.setAddress("133 clos");
	    student.setAge(22);
	    student.setName("Loick");
	    student2.setAddress("22 boulevard");
	    student2.setAge(12);
	    student2.setName("dede");
	    em.getTransaction().begin() ;
	    em.persist(student) ;
	    em.persist(student2);
	    em.getTransaction().commit() ;		
		
	}
		
}