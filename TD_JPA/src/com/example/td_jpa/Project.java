package com.example.td_jpa;


import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity

public class Project implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private long owner_id;
	private static final long serialVersionUID = 1L;

	public Project() {
		super();
	}   
	public long getProjectID() {
		return this.id;
	}

	public void setProjectID(int id) {
		this.id = id;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public long getOwner() {
		return this.owner_id;
	}

	public void setOwner(int owner_id) {
		this.owner_id = owner_id;
	}
   
}
