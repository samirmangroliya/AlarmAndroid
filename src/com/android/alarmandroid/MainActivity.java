/*package com.android.alarmandroid;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	// The alarm action defined in AndroidManifest.xml
	private static final String ALARM_ACTION_NAME = "com.bytefoundry.broadcast.ALARM";

	// Handle for the AlarmManager service
	private AlarmManager alarmManager;

	// An ID for the alarm (used for the PendingIntent request code)
	private int m_alarmId;

	int value = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set the main content view
		setContentView(R.layout.main);

		// Set the default for the alarm ID
		m_alarmId = 0;

		// Get a handle to the AlarmManager service
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

	}

	public void startAlert(View view) {
		EditText text = (EditText) findViewById(R.id.time);

		String strValue = text.getText().toString().trim();
		if (TextUtils.isEmpty(strValue)) {
			showToast("Enter Time For Alarm!!!");
			return;
		}

		value = Integer.parseInt(text.getText().toString());
		m_alarmId++;
		createAlarm();
	}

	private void createAlarm() {
		// Create an alarm intent
		Intent alarmIntent = new Intent(ALARM_ACTION_NAME);

		// Set the Alarm ID as extra data to be displayed in the popup
		alarmIntent.putExtra("AlarmID", m_alarmId);

		// Create the corresponding PendingIntent object
		PendingIntent alarmPI = PendingIntent.getBroadcast(this, m_alarmId,
				alarmIntent, 0);

		// Use a Calendar object to create the alarm time 10 seconds in the
		// future
		Calendar calAlarm = Calendar.getInstance();
		calAlarm.add(Calendar.SECOND, value);

		// Register the alarm with the alarm manager
		alarmManager.set(AlarmManager.RTC_WAKEUP, calAlarm.getTimeInMillis(),
				alarmPI);
		

		showToast("Alarm Set For " + value + " Seconds");
	}

	private void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

	}

}*/