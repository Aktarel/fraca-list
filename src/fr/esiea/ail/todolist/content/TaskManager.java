package fr.esiea.ail.todolist.content;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import fr.esiea.ail.todolist.model.Task;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TaskManager {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<Task> ITEMS = new ArrayList<Task>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	@SuppressLint("UseSparseArrays")
	public static Map<Integer, Task> ITEM_MAP = new HashMap<Integer, Task>();

	static {
		// Add 3 sample items.
		addItem(new Task(1, "Tache number 1",new Date()));
		addItem(new Task(2, "Tache number 2",new Date()));
		addItem(new Task(3, "Tache number 3",new Date()));
		addItem(new Task(4, "Tache number 4",new Date()));
		addItem(new Task(5, "Tache number 5",new Date()));
		addItem(new Task(6, "Tache number 6",new Date()));
	}

	private static void addItem(Task item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.getId(), item);
	}



}
