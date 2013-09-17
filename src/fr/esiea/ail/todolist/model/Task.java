package fr.esiea.ail.todolist.model;

import java.util.Date;

public class Task {

	private int id;
	
	private String name;
	
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Task() {
		// TODO Auto-generated constructor stub
	}

	public Task(int id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}
	@Override
	public String toString() {
		return id +" "+name;
	}
	
}
