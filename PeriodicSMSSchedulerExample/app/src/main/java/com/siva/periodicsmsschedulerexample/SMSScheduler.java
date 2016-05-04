package com.siva.periodicsmsschedulerexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Celestial on 18-04-2016.
 */
public class SMSScheduler {

    private Context context;
    PendingIntent alarmIntent;
    AlarmManager alarmManager;
    Calendar calSet;


    public SMSScheduler(Context context) {
        this.context = context;
    }

    public void scheduleAlarm(ArrayList<Integer> itemsSelected)
    {
        // Set the alarm to start at 8:30 a.m.
       /* Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);*/

        calSet = Calendar.getInstance();

        // http://stackoverflow.com/questions/12507901/how-to-repeat-alarm-week-day-on-in-android

        if (itemsSelected.contains(0)) {
            forday(1);
        }
        if (itemsSelected.contains(1)) {
            forday(2);
        }
        if (itemsSelected.contains(2)) {
            forday(3);
        }
        if (itemsSelected.contains(3)) {
            forday(4);
        }
        if (itemsSelected.contains(4)) {
            forday(5);
        }
        if (itemsSelected.contains(5)) {
            forday(6);
        }
        if (itemsSelected.contains(6)) {
            forday(7);
        }


        //we will write the code to send SMS inside onRecieve() method pf Alarmreciever class
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // create the object
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        //alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(context, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(context, "SMS Scheduled for fixed time", Toast.LENGTH_LONG).show();

    }

    public void forday(int week) {

        calSet.set(Calendar.DAY_OF_WEEK, week);
        calSet.set(Calendar.HOUR_OF_DAY, 8);
        calSet.set(Calendar.MINUTE, 30);
        calSet.set(Calendar.SECOND, 0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calSet.getTimeInMillis(), 24 * 7 * 60 * 60 * 1000, alarmIntent);
    }

}
