package fr.esiea.ail.todolist;

import java.io.IOException;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.esiea.ail.todolist.dao.impl.TaskManagerImpl;
import fr.esiea.ail.todolist.model.Task;

/**
 * A fragment representing a single Task detail screen. This fragment is either
 * contained in a {@link TaskListActivity} in two-pane mode (on tablets) or a
 * {@link TaskDetailActivity} on handsets.
 */
public class TaskDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Task mItem;

	public Task getmItem() {
		return mItem;
	}

	public void setmItem(Task mItem) {
		this.mItem = mItem;
	}

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public TaskDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			try {
				fr.esiea.ail.todolist.dao.TaskManager manager = new TaskManagerImpl(
						getActivity().getApplicationContext(),
						Context.MODE_PRIVATE);
				mItem = manager.get(new Task((Integer) getArguments().get(
						ARG_ITEM_ID)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				fr.esiea.ail.todolist.R.layout.activity_task_detail, container,
				false);
		// Show the dummy content as text in a TextView.
		// Log.e("myApp", mItem.toString());
		if (mItem != null) {
			TextView t = (TextView) rootView
					.findViewById(fr.esiea.ail.todolist.R.id.nomContact);
			t.setText(mItem.getName());
			;
		}
		return rootView;
	}
}
