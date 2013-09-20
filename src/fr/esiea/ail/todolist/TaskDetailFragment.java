package fr.esiea.ail.todolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.esiea.ail.todolist.content.TaskManager;
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
			mItem = TaskManager.ITEM_MAP.get(Integer.parseInt(getArguments().getString(
					ARG_ITEM_ID)));
			
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_task_detail,
				container, false);
		// Show the dummy content as text in a TextView.
		Log.e("myApp", mItem.toString());
		if (mItem != null) {
			TextView t = (TextView)rootView.findViewById(R.id.nomContact);
			t.setText(mItem.getName());;
		}
		return rootView;
	}
}
=======
package fr.esiea.ail.todolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.esiea.ail.todolist.content.TaskManager;
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
			mItem = TaskManager.ITEM_MAP.get(Integer.parseInt(getArguments().getString(
					ARG_ITEM_ID)));
			
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_task_detail,
				container, false);
		// Show the dummy content as text in a TextView.
		Log.e("myApp", mItem.toString());
		if (mItem != null) {
			TextView t = (TextView)rootView.findViewById(R.id.nomContact);
			t.setText(mItem.getName());;
		}
		return rootView;
	}
}
