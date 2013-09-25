package fr.esiea.ail.todolist.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
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

public class TaskArrayAdapter extends ArrayAdapter<Task> implements
		OnTouchListener {
	private float mLastX;
	private List<Task> tasksToDelete;
	private final int MAX_CLICK_DURATION = 400;
	private final int MAX_CLICK_DISTANCE = 5;
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

	@Override
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
			long clickDuration = Calendar.getInstance().getTimeInMillis()
					- startClickTime;
			int thresholdUser = 2;
			x2 = event.getX();
			y2 = event.getY();
			dx = x2 - x1;
			dy = y2 - y1;

			if (clickDuration < MAX_CLICK_DURATION && dx < MAX_CLICK_DISTANCE
					&& dy < MAX_CLICK_DISTANCE) {
				for (int i = 0; i < getCount(); i++) {
					if (this.getItem(i).getName().equals(tv.getText()))
						onClick(tv,this.getItem(i));
				}
				
			}

			if (currentX > mLastX + v.getWidth() / thresholdUser) {
				tv.setPaintFlags(tv.getPaintFlags()
						| Paint.STRIKE_THRU_TEXT_FLAG);
				tv.setBackgroundColor(Color.rgb(139,35,35));

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

	public void onClick(View v,Task t) {
		TaskListActivity activity = (TaskListActivity) v.getContext();
		Intent detailIntent = new Intent(activity, TaskDetailActivity.class);
		detailIntent.putExtra(TaskDetailFragment.ARG_ITEM_ID, t.getId());
		activity.startActivity(detailIntent);
	}
}
