package fr.esiea.ail.todolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
=======
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.esiea.ail.todolist.content.TaskManager;
import fr.esiea.ail.todolist.model.Task;
>>>>>>> dcb8d4095322737f5b6e34a2e61dc65b5b341a18

/**
 * A fragment representing a single Task detail screen. This fragment is either
 * contained in a {@link TaskListActivity} in two-pane mode (on tablets) or a
 * {@link TaskDetailActivity} on handsets.
 */
public class TaskAddFragment extends Fragment {
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public TaskAddFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_task_add,
				container, false);
		return rootView;
	}
<<<<<<< HEAD

=======
>>>>>>> dcb8d4095322737f5b6e34a2e61dc65b5b341a18
}
