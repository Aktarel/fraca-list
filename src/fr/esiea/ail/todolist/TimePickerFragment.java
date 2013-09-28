package fr.esiea.ail.todolist;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

<<<<<<< HEAD
/**
 * 
 * Dialog use in add form You can choose from a time
 * 
 * @author Todolist Team
 * 
 */
public class TimePickerFragment extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener {

=======
public class TimePickerFragment extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener {

	@Override
>>>>>>> 9218e480f7dc3c0cc36b4155635845058d60448e
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,
				DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
<<<<<<< HEAD
		
		if(((EditText) getActivity().findViewById(R.id.textField_time))!=null){
			//for add time field
			((EditText) getActivity().findViewById(R.id.textField_time)).setText(String.valueOf(hourOfDay) + ":"
						+ String.valueOf(minute));
		}
		else{
			//for update time field
			((EditText) getActivity().findViewById(R.id.textField_time_update)).setText(String.valueOf(hourOfDay) + ":"
					+ String.valueOf(minute));
=======
		if (minute < 10) {
			((EditText) getActivity().findViewById(R.id.textField_time))
					.setText(String.valueOf(hourOfDay) + ":0"
							+ String.valueOf(minute));
		} else {
			((EditText) getActivity().findViewById(R.id.textField_time))
					.setText(String.valueOf(hourOfDay) + ":"
							+ String.valueOf(minute));
>>>>>>> 9218e480f7dc3c0cc36b4155635845058d60448e
		}
	}
}