package com.example.td_jpa;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

import com.example.td_jpa.Student;
import com.example.td_jpa.StudentEJB;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
@Theme("td_jpa")
public class Td_jpaUI extends UI {

	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Td_jpaUI.class)
	public static class Servlet extends VaadinServlet {
	}
	

	
	@Override
	protected void init(VaadinRequest request) {
		
		StudentEJB e_s = new StudentEJB();
		ProjectEJB e_p = new ProjectEJB();
		
		TextField nameTextField = new TextField("name");
		TextField addressTextField = new TextField("address");
		TextField ageTextField = new TextField("age");
		TextField idTextField = new TextField("id student");
		TextField ownerPTextField = new TextField("owner");
		TextField titlePTextField = new TextField("title");
		TextField idPTextField = new TextField("id project");
		
		HorizontalLayout layoutH = new HorizontalLayout();
		layoutH.setWidth("1250px");
		setContent(layoutH);

		
		
		// Layout 1
		
		
		//Create a part for : Create a student 
		VerticalLayout layout1 = new VerticalLayout();		
		Button buttonCreate = new Button("Create a Student");
		buttonCreate.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Student s = new Student();
				s.setName(nameTextField.getValue());
				s.setAddress(addressTextField.getValue());
				s.setAge(Integer.parseInt(ageTextField.getValue()));
				try {
					e_s.createStudent(s);
					layout1.addComponent(new Label("A student has been created"));

				}
				catch(Exception e) {
					layout1.addComponent(new Label("Create ERROR"));

				}
			}
		});
		
		layout1.addComponent((Component)nameTextField);
		layout1.addComponent((Component)addressTextField);
		layout1.addComponent((Component)ageTextField);
		layout1.addComponent(new Label(""));
		layout1.addComponent((Component)buttonCreate);
		layoutH.addComponent(layout1);
		layoutH.setComponentAlignment(layout1, Alignment.TOP_CENTER);
		
		
		
		
		// Layout 2
		
		VerticalLayout layout2 = new VerticalLayout();
		
		
		//Create a part for : Find a student by Id 
		
		Button buttonFindId = new Button("Find a Student by Id");
		buttonFindId.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Student s = new Student();
				try {
					s = e_s.findStudentById(Integer.parseInt(idTextField.getValue()));
					nameTextField.setValue(s.getName());
					addressTextField.setValue(s.getAddress());
					ageTextField.setValue(""+s.getAge());
					
					layout2.addComponent(new Label("\nID: " + s.getStudentID() + "\n  | name: " + s.getName() + 
							"\n  | address: " + s.getAddress() +
							"\n  | age: " + s.getAge()));
				}
				catch(Exception e) {
					layout2.addComponent(new Label("Update ERROR"));
				}
			}
			
		});	
		
		//Create a part for : Update a student 		
				
				Button buttonUpdate = new Button("Update a Student");
				buttonUpdate.addClickListener(new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {
					
						try {
							Student s = e_s.findStudentById(Integer.parseInt(idTextField.getValue()));
							s.setName(nameTextField.getValue());
							s.setAddress(addressTextField.getValue());
							s.setAge(Integer.parseInt(ageTextField.getValue()));
							e_s.updateStudent(s);
							layout2.addComponent(new Label("A student has been updated"));
						}
						catch(Exception e) {
							layout2.addComponent(new Label("Update ERROR"));

						}
					}
				});
				
				
				
				//Create a part for : Delete a student by Id
				
				Button buttonDelete = new Button("Delete a Student");
				buttonDelete.addClickListener(new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {
						Student s = new Student();
												
						try {
							e_s.deleteStudent(e_s.findStudentById(Integer.parseInt(idTextField.getValue())));
							List<Project> lproject = new ArrayList<Project>() ;
							lproject=e_s.findProjects(Integer.parseInt(idTextField.getValue()));
							for (Project pp : lproject){
								e_p.deleteProject(pp);
							}
							//e_s.deleteStudent(e_s.findStudentById(Integer.parseInt(idTextField.getValue())));
							//s = e_s.findStudentById(Integer.parseInt(idTextField.getValue()));
							//lproject = e_s.findProjects(s.getStudentID());
							//for (Project pp : lproject){
								//e_p.deleteProject(pp);
							//}
							//e_s.deleteStudent(s);
							layout2.addComponent(new Label("A student has been deleted"));
						}
						catch(Exception e) {
							layout2.addComponent(new Label("Delete ERROR"));
						}
						
					}
				});
				
				
				// find projects by ID student
				
				Button buttonFindProjectS = new Button("Find Projects by ID Student");
				buttonFindProjectS.addClickListener(new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {
						Student s = new Student();
						try {
							s = e_s.findStudentById(Integer.parseInt(idTextField.getValue()));
							
							List<Project> lproject = new ArrayList<Project>() ;
							lproject=e_s.findProjects(s.getStudentID());
							layout2.addComponent(new Label("Projects of student n° "+s.getStudentID()));
							for (Project pp : lproject){
								layout2.addComponent(new Label("\nID: " + pp.getProjectID() + "\n  | title: " + pp.getTitle() + "\n  | owner : " + pp.getOwner() ));
							}
							
						}
						catch(Exception e) {
							layout2.addComponent(new Label("Find all students ERROR"));
						}	
					}
				});
				
				
		layout2.addComponent((Component)idTextField);
		layout2.addComponent(new Label(""));
		layout2.addComponent((Component)buttonFindId);
		layout2.addComponent(new Label(""));
		layout2.addComponent((Component)buttonDelete);
		layout2.addComponent(new Label(""));
		layout2.addComponent((Component)buttonUpdate);
		layout2.addComponent(new Label(""));
		layout2.addComponent((Component)buttonFindProjectS);
		layoutH.addComponent(layout2);
		layoutH.setComponentAlignment(layout2, Alignment.TOP_CENTER);
				
		
		
		
		// Layout 3
		VerticalLayout layout3 = new VerticalLayout();
		
		
		//Create a part for : Find all students 
		

		Button buttonFindAllS = new Button("Find all Students");
		buttonFindAllS.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				List<Student> lStudent = new ArrayList<Student>();
				try {
					lStudent = e_s.findStudents();
					
					layout3.addComponent(new Label("All Students: "));
					for(Student ss : lStudent) {
						layout3.addComponent(new Label("\nID: " + ss.getStudentID() + "\n  | name: " + ss.getName() + 
								"\n  | address: " + ss.getAddress() +
								"\n  | age: " + ss.getAge()));
					}
				}
				catch(Exception e) {
					layout3.addComponent(new Label("Find all students ERROR"));
				}	
			}
		});
		
		
		Button buttonFindAllP = new Button("Find all Projects");
		buttonFindAllP.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				List<Project> lProject = new ArrayList<Project>();
				try {
					lProject = e_p.findProjects();
					layout3.addComponent(new Label("All Projects :"));
					for(Project pp : lProject) {
						layout3.addComponent(new Label("\nID: " + pp.getProjectID() + "\n  | title: " + pp.getTitle() + "\n  | owner : " + pp.getOwner() ));
					}
				}
				catch(Exception e) {
					layout3.addComponent(new Label("Find all projects ERROR"));
				}	
			}
		});
		
		layout3.addComponent((Component)buttonFindAllS);
		layout3.addComponent(new Label(""));
		layout3.addComponent((Component)buttonFindAllP);
		layoutH.addComponent(layout3);
		layoutH.setComponentAlignment(layout3, Alignment.TOP_CENTER);
	
	
	
	// Layout 4
	
			VerticalLayout layout4 = new VerticalLayout();
			
			
			//Create a part for : Find a student by Id 
			
			Button buttonFindIdP = new Button("Find a Project by Id");
			buttonFindIdP.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					Project p = new Project();
					try {
						p = e_p.findProjectById(Integer.parseInt(idPTextField.getValue()));
						titlePTextField.setValue(p.getTitle());
						ownerPTextField.setValue(""+p.getOwner());
												
						layout4.addComponent(new Label("\nID: " + p.getProjectID() + "\n  | title: " + p.getTitle() + "\n  | owner : " + p.getOwner() ));
					}
					catch(Exception e) {
						layout4.addComponent(new Label("Update ERROR"));
					}
				}	
			});	
			
			//Create a part for : Update a student 		
					
					Button buttonUpdateP = new Button("Update a Project");
					buttonUpdateP.addClickListener(new Button.ClickListener() {
						public void buttonClick(ClickEvent event) {
						
							try {
								Project p = e_p.findProjectById(Integer.parseInt(idPTextField.getValue()));
								p.setTitle(titlePTextField.getValue());
								p.setOwner(Integer.parseInt(ownerPTextField.getValue()));
								e_p.updateProject(p);
								layout4.addComponent(new Label("A project has been updated"));
							}
							catch(Exception e) {
								layout4.addComponent(new Label("Update ERROR"));

							}
						}
					});
					
					
					
					//Create a part for : Delete a student by Id
					
					Button buttonDeleteP = new Button("Delete a Project");
					buttonDeleteP.addClickListener(new Button.ClickListener() {
						public void buttonClick(ClickEvent event) {
							try {
								e_p.deleteProject(e_p.findProjectById(Integer.parseInt(idPTextField.getValue())));
								layout4.addComponent(new Label("A project has been deleted"));
							}
							catch(Exception e) {
								layout4.addComponent(new Label("Delete ERROR"));
							}
							
						}
					});
					
					
			layout4.addComponent((Component)idPTextField);
			layout4.addComponent(new Label(""));
			layout4.addComponent((Component)buttonFindIdP);
			layout4.addComponent(new Label(""));
			layout4.addComponent((Component)buttonDeleteP);
			layout4.addComponent(new Label(""));
			layout4.addComponent((Component)buttonUpdateP);
			layoutH.addComponent(layout4);
			layoutH.setComponentAlignment(layout4, Alignment.TOP_CENTER);
	
	// Layout 1
	
	
			//Create a part for : Create a student 
			VerticalLayout layout5 = new VerticalLayout();		
			Button buttonCreateP = new Button("Create a Project");
			buttonCreateP.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					Project p = new Project();
					p.setTitle(titlePTextField.getValue());
					p.setOwner(Integer.parseInt(ownerPTextField.getValue()));
					try {
						List<Student> lstudent = new ArrayList<Student>();
						lstudent = e_s.findStudents();
						Boolean boolStudExist = false;			
						
						
						for (Student ss : lstudent){
							if (ss.getStudentID()==p.getOwner()){
								boolStudExist = true;
							}
						}
						if (boolStudExist == false){
							layout5.addComponent(new Label("ERROR: This student doesn't exist"));
						} else {
							e_p.createProject(p);
							layout5.addComponent(new Label("A project has been created"));
							}
						}
					catch(Exception e) {
						layout5.addComponent(new Label("Create ERROR"));

					}
				}
			});
			
			layout5.addComponent((Component)titlePTextField);
			layout5.addComponent((Component)ownerPTextField);
			layout5.addComponent(new Label(""));
			layout5.addComponent((Component)buttonCreateP);
			layoutH.addComponent(layout5);
			layoutH.setComponentAlignment(layout5, Alignment.TOP_CENTER);
	}
}