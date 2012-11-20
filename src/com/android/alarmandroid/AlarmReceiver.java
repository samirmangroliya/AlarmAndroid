package com.android.alarmandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
	// The alarm action defined in AndroidManifest.xml
	private static final String ALARM_ACTION_NAME = "com.bytefoundry.broadcast.ALARM";

	@Override
	public void onReceive(Context context, Intent intent) {
		// Toast.makeText(context , "Alarm received!",
		// Toast.LENGTH_LONG).show();
		// Handle the alarm broadcast
		if (ALARM_ACTION_NAME.equals(intent.getAction())) {
			// Launch the alarm popup dialog
			Intent alarmIntent = new Intent("android.intent.action.MAIN");

			alarmIntent.setClass(context, AlarmPopUp.class);
			alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Pass on the alarm ID as extra data
			alarmIntent.putExtra("AlarmID", intent.getIntExtra("AlarmID", -1));
			alarmIntent.putExtra("AlarmTime",
					intent.getStringExtra("AlarmTime"));

			// Start the popup activity
			context.startActivity(alarmIntent);
		}
		/*
		 * PackageManager packageManager = context.getPackageManager(); Intent
		 * alarmClockIntent = new Intent(Intent.ACTION_MAIN)
		 * .addCategory(Intent.CATEGORY_LAUNCHER);
		 * 
		 * // Verify clock implementation String clockImpls[][] = { {
		 * "HTC Alarm Clock", "com.htc.android.worldclock",
		 * "com.htc.android.worldclock.WorldClockTabControl" }, {
		 * "Standar Alarm Clock", "com.android.deskclock",
		 * "com.android.deskclock.AlarmClock" }, { "Froyo Nexus Alarm Clock",
		 * "com.google.android.deskclock", "com.android.deskclock.DeskClock" },
		 * { "Moto Blur Alarm Clock", "com.motorola.blur.alarmclock",
		 * "com.motorola.blur.alarmclock.AlarmClock" }, {
		 * "Samsung Galaxy Clock", "com.sec.android.app.clockpackage",
		 * "com.sec.android.app.clockpackage.ClockPackage" } };
		 * 
		 * boolean foundClockImpl = false;
		 * 
		 * for (int i = 0; i < clockImpls.length; i++) { String vendor =
		 * clockImpls[i][0]; String packageName = clockImpls[i][1]; String
		 * className = clockImpls[i][2]; try { ComponentName cn = new
		 * ComponentName(packageName, className); ActivityInfo aInfo =
		 * packageManager.getActivityInfo(cn, PackageManager.GET_META_DATA);
		 * alarmClockIntent.setComponent(cn);
		 * 
		 * foundClockImpl = true; } catch (NameNotFoundException e) {
		 * 
		 * } }
		 * 
		 * if (foundClockImpl) { PendingIntent pendingIntent =
		 * PendingIntent.getActivity(context, 0, alarmClockIntent, 0); // add
		 * pending intent to your component // .... }
		 */
	}
}