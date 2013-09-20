package fr.esiea.ail.todolist.dao;

import java.io.IOException;

import fr.esiea.ail.todolist.model.Task;

public interface TaskManager {

	
	
	public void add(Task t) throws IOException;
	
	public void delete(Task t)  throws IOException;
	
	public void update(Task t) throws IOException;
	
	public Task get(Task t)  throws IOException;
	
	public void init() throws IOException;
}
