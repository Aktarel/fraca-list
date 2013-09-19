package fr.esiea.ail.todolist.dao.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import fr.esiea.ail.todolist.dao.TaskManager;
import fr.esiea.ail.todolist.model.Task;
import fr.esiea.ail.todolist.util.IcsConverter;

public class TaskManagerImpl implements TaskManager {

	private Context context;
	private String fichier = new String("fracaList_datas");
	private IcsConverter converter;
	private String delimiter = "</fin>";
	FileOutputStream fos;

	public TaskManagerImpl(Context c, int mode) throws IOException {
		this.context = c;
		converter = new IcsConverter();
		fos = context.openFileOutput(fichier, mode);
	}

	public void add(Task t) throws IOException {

		StringBuilder csvFormat = new StringBuilder();
		csvFormat.append(t.getId() + delimiter);
		csvFormat.append(t.getName() + delimiter);
		csvFormat.append(t.getDate().getTime() + delimiter + "\n");

		fos.write(csvFormat.toString().getBytes());
		fos.close();

	}

	public void delete(Task t) throws IOException {

		List<Task> tasks = list();
		tasks.remove(t);

		for (Task task : tasks)
			add(task);

	}

	public void update(Task t) throws IOException {
		
		List<Task> taches = list();
		
		for(Task tache : taches){
			if(t.equals(tache)){
				taches.remove(tache);
			}
		}
		
		taches.add(t);
	
		
		
	}

	public Task get(Task t) throws IOException {

		List<Task> taches = list();
		for (Task tache : taches) {
			if (t.equals(tache)) {
				return t;
			}
		}
		return new Task();
	}

	public List<Task> list() throws IOException {

		int content;
		FileInputStream fis = context.openFileInput(fichier);
		StringBuilder builder = new StringBuilder();
		List<Task> mesTaches = new ArrayList<Task>();

		while ((content = fis.read()) != -1) {
			builder.append((char) content);
		}

		String[] mesObjets = builder.toString().split("\n");

		for (int i = 0; i < mesObjets.length; i++) {
			String[] mesAttributs = mesObjets[i].split("</fin>");
			mesTaches
			.add(new Task(Integer.parseInt(mesAttributs[0]),
					mesAttributs[1], new Date(Long
							.parseLong(mesAttributs[2]))));
		}

		fis.close();

		return mesTaches;

	}

}
