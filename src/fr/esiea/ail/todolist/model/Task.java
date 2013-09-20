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
		return id+" "+name+" "+date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}

