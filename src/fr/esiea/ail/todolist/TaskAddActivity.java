package fr.esiea.ail.todolist;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import fr.esiea.ail.todolist.dao.impl.TaskManagerImpl;
import fr.esiea.ail.todolist.model.Task;

/**
 * An activity representing a single Task detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link TaskListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link TaskDetailFragment}.
 */
public class TaskAddActivity extends FragmentActivity {

	private SimpleDateFormat timeFormat;
	private SimpleDateFormat dateFormat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_add);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		final EditText nameTask = ((EditText) findViewById(R.id.textInput_task_name));
		final EditText commentTask = ((EditText) findViewById(R.id.textInput_task_comment));

		final EditText timeTask = ((EditText) findViewById(R.id.textField_time));
		final EditText dateTask = ((EditText) findViewById(R.id.textField_date));

		Date today = new Date();
		timeFormat = new SimpleDateFormat("HH:mm");

		// English format date asked
		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		timeTask.setText(timeFormat.format(today));
		dateTask.setText(dateFormat.format(today));

		nameTask.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View arg0, boolean arg1) {
				nameTask.setError(null);
			}
		});

		commentTask.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View arg0, boolean arg1) {
				commentTask.setError(null);
			}
		});

	}

	/**
	 * Menu event listener whenever user click on menu items
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// Default Home button on top bar menu trigger
			NavUtils.navigateUpTo(this,
					new Intent(this, TaskListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 
	 * Init our top menu bar with component in our add form Task view
	 * 
	 * @param Menu
	 *            menu : current menu bar on top
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		menu.removeItem(R.id.button_actionbar_add);
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

	/**
	 * Method called when user press submit form button
	 * 
	 * @param view
	 * @throws IOException
	 * @throws ParseException
	 */
	public void addTask(View view) throws IOException, ParseException {
		TaskManagerImpl tmi = null;
		boolean validName = true, validComment = true;
		EditText nameTask = ((EditText) findViewById(R.id.textInput_task_name));
		EditText commentTask = ((EditText) findViewById(R.id.textInput_task_comment));
		EditText dateTask = ((EditText) findViewById(R.id.textField_date));

		// Validate data and ask user to fill if empty
		validName = validate(nameTask, "Task 'name' empty, please fill it!");
		validComment = validate(commentTask,
				"Task 'comment' empty, please fill it!");
		if (validName && validComment) {
			tmi = new TaskManagerImpl(getApplicationContext(), MODE_APPEND);
			tmi.add(new Task(nameTask.getText().toString(), dateTask.getText()
					.toString()));
			Log.e("myApp", "On tente d'inserer la tache >"
					+ nameTask.getText().toString() + " "
					+ dateTask.getText().toString());
			NavUtils.navigateUpTo(this,
					new Intent(this, TaskListActivity.class));
		}

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
