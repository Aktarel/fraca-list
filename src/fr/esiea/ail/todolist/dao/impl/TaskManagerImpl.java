package fr.esiea.ail.todolist.dao.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import fr.esiea.ail.todolist.dao.TaskManager;
import fr.esiea.ail.todolist.model.Task;

/**
 * 
 * TaskManagerImpl is our main implementation of our services
 * 
 * @since 21/09/2013
 * @author Todo-List TEAM
 * 
 */

public class TaskManagerImpl implements TaskManager {

	/* name of the data storage file */
	private String fichier = new String("fracaList_datas");

	/* our delimiter */
	private String delimiter = "</fin>";

	/* Must be declared if you want to access datafile storage */
	private Context context;

	/* Specify if you append or erase in the data file */
	private int mode;

	public TaskManagerImpl(Context c, int mode) throws IOException {
		this.context = c;
		this.mode = mode;
	}

	/**
	 * Add new task {@link Task}
	 * 
	 * @param Task
	 *            : what we want to add
	 */
	public void add(Task t) throws IOException {

		FileOutputStream fos = context.openFileOutput(fichier, mode);

		StringBuilder csvFormat = new StringBuilder();
		csvFormat.append(getFreeIndexSequence() + delimiter);
		csvFormat.append(t.getName() + delimiter);
		csvFormat.append(t.getDate().getTime() + delimiter + "\n");

		fos.write(csvFormat.toString().getBytes());
		fos.close();

	}

	/**
	 * Delete the task (see also equals method in {@link Task})
	 * 
	 * @param Task
	 *            : what we want to delete
	 */
	public void delete(Task t) throws IOException {

		List<Task> tasks = list();
		tasks.remove(t);
		addList(tasks);

	}

	/**
	 * Not in the interface {@link TaskManager} Add in list mode (should be
	 * faster than add method if you add more than one object)
	 * 
	 * @param tasks
	 *            : what we want inside the file
	 * @throws IOException
	 */
	private void addList(List<Task> tasks) throws IOException {

		int modeAdd = Context.MODE_PRIVATE;
		FileOutputStream fos = context.openFileOutput(fichier, modeAdd);

		for (Task t : tasks) {
			StringBuilder csvFormat = new StringBuilder();
			csvFormat.append(getFreeIndexSequence() + delimiter);
			csvFormat.append(t.getName() + delimiter);
			csvFormat.append(t.getDate().getTime() + delimiter + "\n");
			fos.write(csvFormat.toString().getBytes());
		}

		fos.close();
	}

	/**
	 * Update a Task object
	 * 
	 */
	public void update(Task t) throws IOException {

		List<Task> tasks = list();

		for (Task task : tasks) {
			if (t.equals(task)) {
				tasks.remove(task);
			}
		}

		tasks.add(t);

	}

	/**
	 * Retrieve a Task object
	 * 
	 * @return our desired Task
	 */
	public Task get(Task t) throws IOException {

		List<Task> taches = list();
		for (Task tache : taches) {
			if (t.equals(tache)) {
				return tache;
			}
		}
		return new Task();
	}

	/**
	 * 
	 * List what's inside our internal datastorage
	 * 
	 * @return List of tasks
	 */
	public List<Task> list() throws IOException {

		int content;
		FileInputStream fis = context.openFileInput(fichier);
		StringBuilder builder = new StringBuilder();
		List<Task> tasks = new ArrayList<Task>();

		// Search and extract bytes from files
		while ((content = fis.read()) != -1) {
			builder.append((char) content);
		}

		fis.close();

		// If we get datas the file is not empty
		if (builder.length() != 0) {
			// \n is a delimiter between objects
			String[] myObjects = builder.toString().split("\n");

			for (int i = 0; i < myObjects.length; i++) {

				// Format is simple and should respect this architecture :
				// value1</fin>value2</fin>value3</fin>valueN</fin>
				// </fin> is the delimiter between attributes
				String[] attributes = myObjects[i].split("</fin>");
				tasks.add(new Task(Integer.parseInt(attributes[0]),
						attributes[1], new Date(Long.parseLong(attributes[2]))));
			}
			return tasks;
		} else {

			return new ArrayList<Task>();
		}

	}

	/**
	 * We need to assign an unique id in case of insert in relational database
	 * so we generate the last id of the list + 1 It should do the trick for
	 * unicity constraint
	 * 
	 * @return the last id allow for the futur object we will add
	 * @throws IOException
	 */
	public int getFreeIndexSequence() throws IOException {
		List<Task> tasks = list();
		if (!tasks.isEmpty()) {
			return tasks.get(tasks.size() - 1).getId() + 1;
		} else {
			return 0;
		}

	}

}
