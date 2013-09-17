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
		addItem(new Task(1, "Slap that black bitch",new Date()));
		addItem(new Task(2, "Kick that fucking remy",new Date()));
		addItem(new Task(3, "Sex with girlfriend",new Date()));
	}

	private static void addItem(Task item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.getId(), item);
	}


}
