package com.example.td_jpa;

import java.util.List;
import javax.ejb.Remote;

//Interface used by the class ProjectEJB

@Remote
public interface ProjectEJBRemote {
	List<Project> findProjects()  throws Exception ;
	Student findProjectById(long id)  throws Exception ;
	Student createProject(Project p)  throws Exception ;
	void deleteProject(Project p)  throws Exception;
	Student updateProject(Project p)  throws Exception ;
}
