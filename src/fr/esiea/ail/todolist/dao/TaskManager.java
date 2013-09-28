package fr.esiea.ail.todolist.dao;

import java.io.IOException;
import java.util.List;

import fr.esiea.ail.todolist.dao.impl.TaskManagerImpl;
import fr.esiea.ail.todolist.model.Task;

/**
 * 
 * Data Access Object interface : allow you to CRUD object from internal datastorage
 * 
 * @since 21/09/2013
 * @author Todo-List TEAM
 * @see TaskManagerImpl
 *
 */
public interface TaskManager {

	
	/**
	 * Add new task {@link Task}
	 * @param Task : what we want to add
	 */
	public void add(Task t) throws IOException;
	
	/**
	 *  Delete the task (see also equals method in {@link Task})
	 *  @param Task : what we want to delete
	 */
	public void delete(Task t)  throws IOException;
	
	
	/**
	 * Update a Task object
	 * 
	 */
	public void update(Task t) throws IOException;

	/**
	 * Retrieve a Task object 
	 * @return our desired Task
	 */
	public Task get(Task t)  throws IOException;
	

	/**
	 * List what's inside our internal datastorage
	 * @return List of tasks
	 */
	public List<Task> list() throws IOException;
	
}
