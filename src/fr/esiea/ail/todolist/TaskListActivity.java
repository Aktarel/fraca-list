package fr.esiea.ail.todolist;

import java.io.IOException;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import fr.esiea.ail.todolist.dao.TaskManager;
import fr.esiea.ail.todolist.dao.impl.TaskManagerImpl;
import fr.esiea.ail.todolist.model.Task;
import fr.esiea.ail.todolist.util.TaskArrayAdapter;

/**
 * An activity representing a list of Tasks. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link TaskDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link TaskListFragment} and the item details (if present) is a
 * {@link TaskDetailFragment}.
 * <p>
 * This activity also implements the required {@link TaskListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class TaskListActivity extends FragmentActivity implements
		TaskListFragment.Callbacks {

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_list);

		if (findViewById(R.id.task_detail_container) != null) {

			((TaskListFragment) getSupportFragmentManager().findFragmentById(
					R.id.task_list)).setActivateOnItemClick(true);
		}

	}

	/**
	 * Callback method from {@link TaskListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 * 
	 * 
	 */
	public void onItemSelected(String id) {

		// In single-pane mode, simply start the detail activity
		// for the selected item ID.
		Intent detailIntent = new Intent(this, TaskDetailActivity.class);
		detailIntent.putExtra(TaskDetailFragment.ARG_ITEM_ID, id);
		startActivity(detailIntent);
	}

	/**
	 * Create dispatching between views whenever user click on bat top button in
	 * TaskListActivity view
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpTo(this,
					new Intent(this, TaskListActivity.class));
			return true;
		case R.id.button_actionbar_add:
			startActivity(new Intent(this, TaskAddActivity.class));
			return true;
		case R.id.button_actionbar_delete:
			// Ask if user really want to delete tasks
			final TaskListFragment fa = ((TaskListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.task_list));
			final List<Task> taskToDelete = ((TaskArrayAdapter) fa.getListAdapter())
					.getDeletedItems();
			
			if(taskToDelete.isEmpty()){
				Toast.makeText(TaskListActivity.this,"You didn't selected datas, try swipe left to right to delete a task", Toast.LENGTH_LONG).show();
			}
			else{
			AlertDialog dialog = new AlertDialog.Builder(this).create();
			dialog.setTitle("Confirmation");
			dialog.setMessage("Are you sure you want to remove those tasks ?");
			dialog.setCancelable(false);
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int buttonId) {
							deleteTaskFromAdapter();
						}
					});
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int buttonId) {
							
						}
					});
			dialog.setIcon(android.R.drawable.ic_dialog_alert);
			dialog.show();

			return true;
			}

		}
		return super.onOptionsItemSelected(item);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 
	 * Method called in TaskArrayAdapter whenever user want to delete task
	 * 
	 */
	private void deleteTaskFromAdapter() {
		TaskManager manager = null;
		try {
			manager = new TaskManagerImpl(getApplicationContext(),
					Context.MODE_PRIVATE);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		TaskListFragment fa = ((TaskListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.task_list));
		List<Task> taskToDelete = ((TaskArrayAdapter) fa.getListAdapter())
				.getDeletedItems();

		for (Task t : taskToDelete) {

			try {
				manager.delete(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
