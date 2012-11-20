package com.android.alarmandroid;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class AndroidTimeActivity extends Activity {

	TimePicker myTimePicker;
	Button buttonstartSetDialog;
	TextView textDatePrompt, textTimePrompt;

	TimePickerDialog timePickerDialog;

	final static int RQS_1 = 1;

	private int m_alarmId = 0;
	private static final String ALARM_ACTION_NAME = "com.bytefoundry.broadcast.ALARM";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		textDatePrompt = (TextView) findViewById(R.id.alarmprompt);
		textTimePrompt = (TextView) findViewById(R.id.timeprompt);

		buttonstartSetDialog = (Button) findViewById(R.id.startSetDialog);
		buttonstartSetDialog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textDatePrompt.setText("");
				textTimePrompt.setText("");
				openTimePickerDialog(false);

			}
		});

	}

	private void openTimePickerDialog(boolean is24r) {
		Calendar calendar = Calendar.getInstance();

		timePickerDialog = new TimePickerDialog(AndroidTimeActivity.this,
				onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE)+1, is24r);
		timePickerDialog.setTitle("Set Alarm Time");

		timePickerDialog.show();

	}

	OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

			Calendar calNow = Calendar.getInstance();
			Calendar calSet = (Calendar) calNow.clone();

			m_alarmId++;
			calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
			calSet.set(Calendar.MINUTE, minute);
			calSet.set(Calendar.SECOND, 0);
			calSet.set(Calendar.MILLISECOND, 0);

			if (calSet.compareTo(calNow) <= 0) {
				// Today Set time passed, count to tomorrow
				calSet.add(Calendar.DATE, 1);
			}

			setAlarm(calSet);
		}
	};

	private void setAlarm(Calendar targetCal) {
		int hours = targetCal.get(Calendar.HOUR);
		int mnts = targetCal.get(Calendar.MINUTE);
		int ampm = targetCal.get(Calendar.AM_PM);

		String str = "AM";
		if (ampm == 0) {
			str = "AM";
		} else {
			str = "PM";
		}

		String time = hours + " : " + mnts + " : " + str;
		textTimePrompt.setText(time);
		textDatePrompt.setText("\n\n***\n" + "Alarm is set@ "
				+ targetCal.getTime() + "\n" + "***\n");

		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent alarmIntent = new Intent(ALARM_ACTION_NAME);

		// Set the Alarm ID as extra data to be displayed in the popup
		alarmIntent.putExtra("AlarmID", m_alarmId);
		alarmIntent.putExtra("AlarmTime", time);

		// Create the corresponding PendingIntent object
		PendingIntent alarmPI = PendingIntent.getBroadcast(this, m_alarmId,
				alarmIntent, 0);

		// Register the alarm with the alarm manager
		alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
				alarmPI);
	}

}