package fr.esiea.ail.todolist.thread;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.util.Log;
import fr.esiea.ail.todolist.dao.TaskManager;
import fr.esiea.ail.todolist.dao.impl.TaskManagerImpl;
import fr.esiea.ail.todolist.model.Task;

public class ReminderThread extends Thread {

	private TaskManager manager;


	private List<Task> tasks;

	public ReminderThread(Context c, int mode) throws IOException {

		setManager(new TaskManagerImpl(c, mode));
		tasks = manager.list();

	}

	public synchronized void run() {

		
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
			c.set(Calendar.HOUR, c.get(Calendar.HOUR) - 3);

			for (Task taskToDo : tasks) {
				Log.e("myApp", "Thread en route : " + c.getTime() + " != ?"
						+ sdf.format(taskToDo.getDate()));
				
				if (c.before(sdf.format(taskToDo.getDate()))) {
					Log.e("myApp", "La tache va arrivée!");
				}

		}
	}

	public TaskManager getManager() {
		return manager;
	}

	public void setManager(TaskManager manager) {
		this.manager = manager;
	}

}
