package fr.esiea.ail.todolist.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.esiea.ail.todolist.TaskDetailActivity;
import fr.esiea.ail.todolist.TaskDetailFragment;
import fr.esiea.ail.todolist.TaskListActivity;
import fr.esiea.ail.todolist.model.Task;

/**
 * TaskArrayAdapter is the component inside our first main page that group list
 * of Task
 * 
 * OnTouch listener works with 3 steps : >touch screen and keep your finger on
 * the screen >then move >release your finger
 * 
 * @author Akta
 * @since 25/09/2013
 * 
 */
public class TaskArrayAdapter extends ArrayAdapter<Task> implements
		OnTouchListener {
	private float mLastX;

	private List<Task> tasksToDelete;

	/* user threshold we define as a click */
	private final int MAX_CLICK_DURATION = 400;
	private final int MAX_CLICK_DISTANCE = 5;

	/*
	 * clickTime listen whenever the user touch the screen and stop when user
	 * release his finger from the touch screen
	 */
	private long startClickTime = 0;

	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private float dx;
	private float dy;

	public TaskArrayAdapter(Context context, int resource,
			int textViewResourceId, List<Task> objects) {

		super(context, resource, textViewResourceId, objects);
		tasksToDelete = new ArrayList<Task>();

	}

	/**
	 * 
	 * On touch listener
	 * 
	 * @param View
	 *            v : on which object we swipe or click ; MotionEvent event :
	 *            user interaction capture
	 * 
	 */
	public boolean onTouch(View v, MotionEvent event) {

		float currentX = event.getX();

		TextView tv = (TextView) v;

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastX = currentX;
			x1 = event.getX();
			y1 = event.getY();
			startClickTime = Calendar.getInstance().getTimeInMillis();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			// actual click duration
			long clickDuration = Calendar.getInstance().getTimeInMillis()
					- startClickTime;

			// we you divided name object by two
			int thresholdUser = 2;
			x2 = event.getX();
			y2 = event.getY();
			dx = x2 - x1;
			dy = y2 - y1;

			/* simulate onClick method */
			if (clickDuration < MAX_CLICK_DURATION && dx < MAX_CLICK_DISTANCE
					&& dy < MAX_CLICK_DISTANCE) {
				for (int i = 0; i < getCount(); i++) {
					if (this.getItem(i).getName().equals(tv.getText()))
						onClick(tv, this.getItem(i));
				}

			}

			/*
			 * if you want to delete a task, just press with you finger from the
			 * left to the right and release the finger
			 */
			if (currentX > mLastX + v.getWidth() / thresholdUser) {
				tv.setPaintFlags(tv.getPaintFlags()
						| Paint.STRIKE_THRU_TEXT_FLAG);
				// add red background to notify you want to delete the task
				tv.setBackgroundColor(Color.rgb(139, 35, 35));

				for (int i = 0; i < getCount(); i++) {
					if (this.getItem(i).getName().equals(tv.getText()))
						tasksToDelete.add(this.getItem(i));
				}
			}
			break;
		}
		return true;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = super.getView(position, convertView, parent);
		v.setOnTouchListener(this);
		return v;
	}

	public List<Task> getDeletedItems() {

		return tasksToDelete;
	}

	public void onClick(View v, Task t) {
		TaskListActivity activity = (TaskListActivity) v.getContext();
		Intent detailIntent = new Intent(activity, TaskDetailActivity.class);
		Log.e("myApp", "Task : "+t.getId()+" "+t.getName());
		detailIntent.putExtra(TaskDetailFragment.ARG_ITEM_ID, t.getId());
		activity.startActivity(detailIntent);
	}
}
