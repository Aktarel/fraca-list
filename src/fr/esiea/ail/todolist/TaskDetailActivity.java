package fr.esiea.ail.todolist;

import java.io.IOException;
import java.text.ParseException;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import fr.esiea.ail.todolist.model.Task;

/**
 * An activity representing a single Task detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link TaskListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link TaskDetailFragment}.
 */

public class TaskDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putInt(TaskDetailFragment.ARG_ITEM_ID, getIntent()
					.getExtras().getInt(TaskDetailFragment.ARG_ITEM_ID));
			TaskDetailFragment fragment = new TaskDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.task_detail_container, fragment).commit();
		}
		
		
		

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this,
					new Intent(this, TaskListActivity.class));
			return true;
		case R.id.button_actionbar_add:
			startActivity(new Intent(this, TaskAddActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Add menu items in top bar menu
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		menu.removeItem(R.id.button_actionbar_delete);
		return super.onCreateOptionsMenu(menu);
	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getFragmentManager(), "timePicker");
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}

	public void updateTask(View view) throws IOException, ParseException {

		TaskDetailFragment fa = ((TaskDetailFragment) getSupportFragmentManager()
				.findFragmentById(R.id.task_detail_container));

		boolean validName = true, validComment = true;
		EditText nameTask = ((EditText) findViewById(R.id.textInput_task_name_update));
		EditText commentTask = ((EditText) findViewById(R.id.textInput_task_comment_update));
		EditText dateTask = ((EditText) findViewById(R.id.textField_date_update));
		EditText timeTask = ((EditText) findViewById(R.id.textField_time_update));

		// Validate data and ask user to fill if empty
		validName = validate(nameTask, "Task 'name' empty, please fill it!");
		validComment = validate(commentTask,
				"Task 'comment' empty, please fill it!");
		Log.e("myApp", "Update ");
		if (validName && validComment) {
			fa.updateTask(
					new Task(nameTask.getText().toString(), dateTask.getText()
							.toString() + " " + timeTask.getText().toString(),
							commentTask.getText().toString()), view);
			
		}

	
		NavUtils.navigateUpTo(this, new Intent(this, TaskListActivity.class));
	}

	/**
	 * Validate datas
	 * 
	 * @param et
	 *            : text field affected
	 * @param error
	 *            : msg you want user to read if datas wrong
	 * @return
	 */
	public boolean validate(EditText et, String error) {

		if (et.length() == 0) {
			et.setError(error);
			return false;
		}
		return true;
	}

}
