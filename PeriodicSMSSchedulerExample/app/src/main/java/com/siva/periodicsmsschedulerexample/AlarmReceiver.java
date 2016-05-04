package com.siva.periodicsmsschedulerexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // here you can start an activity or service depending on your need
        // for ex you can start an activity to vibrate phone or to ring the phone

        String phoneNumberReciver="9966222600";// phone number to which SMS to be send
        String message="Hi I will be there later, See You soon";// message to send
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
        // Show the toast  like in above screen shot
        Toast.makeText(context, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();

       /* new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
    }
}
