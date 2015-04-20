package com.example.td_jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/* 
Implementation class of Enterprise Java Bean class : ProjectEJB
*/

public class ProjectEJB {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TD_JPA");
    EntityManager em = emf.createEntityManager() ;
	
    public ProjectEJB() {
	}
    
 // Find a project in database thanks to his ID
	public Project findProjectById(long id)  throws Exception {
		return em.find(Project.class, id);
	}

	// Create a project in database with in input an object of type Project	
	public Project createProject(Project p)  throws Exception {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit() ;
		return p;
	}

	// Delete a project in database with in input an object of type Project 
	public void deleteProject(Project p)  throws Exception {
		em.getTransaction().begin();
		p = em.merge(p);
		em.remove(p);
		em.getTransaction().commit() ;
	}

	// Update the fields of a project saved in database 
	public Project updateProject(Project p)  throws Exception  {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit() ;
		return p;
	}
	
	// Find all projects saved in database and return a List which contains all projects
	@SuppressWarnings("unchecked")
	public List<Project> findProjects()  throws Exception {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT p FROM Project as p");
		em.getTransaction().commit() ;
		return query.getResultList();
	}

}
