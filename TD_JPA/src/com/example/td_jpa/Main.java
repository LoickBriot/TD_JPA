package com.example.td_jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) throws Exception {		
		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TD_JPA") ;
		EntityManager em = emf.createEntityManager() ;
		Student marin =  new Student() ;
		Student marin2 =  new Student() ;
	    marin.setAddress("133 clos");
	    marin.setAge(22);
	    marin.setName("Loick");
	    marin2.setAddress("22 boulevard");
	    marin2.setAge(12);
	    marin2.setName("dede");
	    em.getTransaction().begin() ;
	    em.persist(marin) ;
	    em.persist(marin2);
	    em.getTransaction().commit() ;
	    List<Project> p = new ArrayList<Project>();
	    StudentEJB i = new StudentEJB();
	    ProjectEJB e_p = new ProjectEJB();
	    p= i.findProjects(1L);
	    System.out.println("Id = " + marin2.getStudentID()) ;
	    System.out.println("Id = " + marin.getName()) ;
	    //System.out.println(i.findProjects(1L).get(0).getTitle());
	    Student s = new Student();
	    //i.deleteStudent(i.findStudentById(851));
		List<Project> lproject = new ArrayList<Project>() ;
		lproject=i.findProjects(701);
		for (Project pp : lproject){
			e_p.deleteProject(pp);
		}
		
		
	}
		
}