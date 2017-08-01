package com.siva.periodicsmsschedulerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/* Main Activity */

public class MainActivity extends Activity {

    TextView tv_SetTime, tv_setDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_SetTime = (TextView) findViewById(R.id.tv_Time_set);
        tv_setDays = (TextView) findViewById(R.id.tv_Days_Set);
    }

    public void setTime(View v) {
        showTimePickerDialog();
    }

    private void showTimePickerDialog() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tv_SetTime.setText(selectedHour + " : " + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Set Time");
        mTimePicker.show();
    }

    public void setDays(View v) {
        showDaysDialog();
    }

    private void showDaysDialog() {
        Dialog dialog;
        final String[] items = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        final ArrayList<Integer> itemsSelected = new ArrayList<Integer>();

        //final TextView textViewSetTime = (TextView) rowView.findViewById(R.id.textViewSetTime);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SELECT DAYS");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedItemId,
                                        boolean isSelected) {
                        if (isSelected) {
                            itemsSelected.add(selectedItemId);
                            Collections.sort(itemsSelected);
                        } else if (itemsSelected.contains(selectedItemId)) {
                            itemsSelected.remove(Integer.valueOf(selectedItemId));
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Your logic when OK button is clicked
                        String msg="";
                        for (int i = 0; i < itemsSelected.size(); i++) {

                            msg=msg+"\n"+(i+1)+" : "+items[itemsSelected.get(i)];
                        }
                        // txtView.setText(itemsSelected.toString());
                        tv_setDays.setText(msg);
                        new SMSScheduler(MainActivity.this).scheduleAlarm(itemsSelected);

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        dialog = builder.create();
        dialog.show();
    }


}
