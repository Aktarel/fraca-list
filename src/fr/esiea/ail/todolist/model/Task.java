package fr.esiea.ail.todolist.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Task is our main model class It represents a Task : something we want to do
 * 
 * @author Todo-List TEAM
 * 
 */
public class Task {

	/* Id of our Task */
	private int id;

	/* Name of task */
	private String name;

	/* date task should be done */
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

	public Task(String name, String date) throws ParseException {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

		this.name = name;
		this.date = sdf.parse(date);
	}

	public Task(int id) {
		super();
		this.id = id;
	}

	public String toString() {
		return name;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

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
