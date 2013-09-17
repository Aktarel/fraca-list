package fr.esiea.ail.todolist.dao.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import fr.esiea.ail.todolist.dao.TaskManager;
import fr.esiea.ail.todolist.model.Task;
import fr.esiea.ail.todolist.util.IcsConverter;

public class TaskManagerImpl implements TaskManager{

	
	
	private Context context;
	private String fichier = new String("fracaList_datas");
	private IcsConverter converter;
	FileOutputStream fos;
	
	public TaskManagerImpl(Context c) throws FileNotFoundException {
		this.context = c;
		converter = new IcsConverter();
		fos = context.openFileOutput(fichier, Context.MODE_PRIVATE);
	}

	public void add(Task t) throws IOException {
		
		FileOutputStream fos = context.openFileOutput(fichier, Context.MODE_PRIVATE);
		fos.close();
		
	}

	public void delete(Task t) throws IOException {
		
		FileOutputStream fos = context.openFileOutput(fichier, Context.MODE_PRIVATE);
		fos.close();
		
	}

	public void update(Task t) throws IOException {
		FileOutputStream fos = context.openFileOutput(fichier, Context.MODE_PRIVATE);
		fos.close();
	}

	public Task get(Task t) throws IOException {
		FileOutputStream fos = context.openFileOutput(fichier, Context.MODE_PRIVATE);
		fos.close();
		return null;
	}
	
	
	
	
	
}
