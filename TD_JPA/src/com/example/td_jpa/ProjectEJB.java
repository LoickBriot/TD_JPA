package com.example.td_jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProjectEJB {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TD_JPA");
    EntityManager em = emf.createEntityManager() ;
	
    public ProjectEJB() {
	}
    
	
	public Project findProjectById(long id)  throws Exception {
		return em.find(Project.class, id);
	}

	
	public Project createProject(Project p)  throws Exception {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit() ;
		return p;
	}

	
	public void deleteProject(Project p)  throws Exception {
		em.getTransaction().begin();
		p = em.merge(p);
		em.remove(p);
		em.getTransaction().commit() ;
	}

	
	public Project updateProject(Project p)  throws Exception  {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit() ;
		return p;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Project> findProjects()  throws Exception {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT p FROM Project as p");
		em.getTransaction().commit() ;
		return query.getResultList();
	}

}
