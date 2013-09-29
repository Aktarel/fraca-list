package fr.esiea.ail.todolist;

import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * 
 * Dialog use in add form You can choose from a date android default component
 * 
 * @author Todolist Team
 * 
 */
public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	public DatePickerFragment() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		int day, month, year;

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		
		EditText dateTask ;
		if (getActivity().getClass().equals(TaskDetailActivity.class)) {
			dateTask =((EditText) getActivity().findViewById(R.id.textField_date_update));
			dateTask.setText(String.valueOf(month+1) + "/" + String.valueOf(day)
					+ "/" + String.valueOf(year));
			
		}
		else{
			dateTask =((EditText) getActivity().findViewById(R.id.textField_date));
			dateTask.setText(String.valueOf(month+1) + "/" + String.valueOf(day)
					+ "/" + String.valueOf(year));
			
		}
	}
}
