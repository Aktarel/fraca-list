package fr.esiea.ail.todolist.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import fr.esiea.ail.todolist.dao.TaskManager;
import fr.esiea.ail.todolist.model.Task;

public class TaskManagerImpl implements TaskManager {

	private String fichier = new String("fracaList_datas");
	private String delimiter = "</fin>";
	private Context context;
	private int mode;

	public TaskManagerImpl(Context c, int mode) throws IOException {
		this.context = c;
		this.mode = mode;
	}

	public void add(Task t) throws IOException {

		FileOutputStream fos = context.openFileOutput(fichier, mode);

		StringBuilder csvFormat = new StringBuilder();
		csvFormat.append(getFreeIndexSequence() + delimiter);
		csvFormat.append(t.getName() + delimiter);
		csvFormat.append(t.getDate().getTime() + delimiter + "\n");

		Log.e("myApp", "On insère dans la base > " + csvFormat.toString());

		fos.write(csvFormat.toString().getBytes());
		fos.close();

	}

	public void delete(Task t) throws IOException {

		List<Task> tasks = list();
		tasks.remove(t);
		addListe(tasks);

	}

	private void addListe(List<Task> tasks) throws IOException {

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

	public void update(Task t) throws IOException {

		List<Task> taches = list();

		for (Task tache : taches) {
			if (t.equals(tache)) {
				taches.remove(tache);
			}
		}

		taches.add(t);

	}

	public Task get(Task t) throws IOException {

		List<Task> taches = list();
		for (Task tache : taches) {
			if (t.equals(tache)) {
				return tache;
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
		fis.close();

		if (builder.length() != 0) {
			String[] mesObjets = builder.toString().split("\n");

			for (int i = 0; i < mesObjets.length; i++) {
				String[] mesAttributs = mesObjets[i].split("</fin>");
				mesTaches.add(new Task(Integer.parseInt(mesAttributs[0]),
						mesAttributs[1], new Date(Long
								.parseLong(mesAttributs[2]))));
			}
			return mesTaches;
		} else {

			return new ArrayList<Task>();
		}

	}

	public int getFreeIndexSequence() throws IOException {
		List<Task> taches = list();
		if (!taches.isEmpty()) {
			return taches.get(taches.size() - 1).getId() + 1;
		} else {
			return 0;
		}

	}

}
