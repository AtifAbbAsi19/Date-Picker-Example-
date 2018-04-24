package com.muhammadatif.datepicker.dateexample.dialogfragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.muhammadatif.datepicker.dateexample.MainActivity;
import com.muhammadatif.datepicker.dateexample.R;
import com.muhammadatif.datepicker.dateexample.globals.Constants;
import com.muhammadatif.datepicker.dateexample.utilities.Tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Atif Arif on 1/17/2018.
 */

public class DateTimeDialog extends DialogFragment {

    Dialog dialog;

    TextView dateBtn, timeBtn;

    public DateTimeDialog() {

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.datetimedialog);

        dateBtn = (TextView) dialog.findViewById(R.id.dateBtn);
        timeBtn = (TextView) dialog.findViewById(R.id.timeBtn);


        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        return dialog;
    }


    private void showTimePicker() {


        TimePickerFragment timePickerFragment = new TimePickerFragment();

        timePickerFragment.setCallBack(timePickerListener);
//

        android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();

        timePickerFragment.show(manager, "TimePicker");

    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {


                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, selectedHour);// for 6 hour
                    calendar.set(Calendar.MINUTE, selectedMinute);// for 0 min


                    Date currentLocalTime = calendar.getTime();
                    DateFormat date = new SimpleDateFormat("hh:mm a");
                    String localTime = date.format(currentLocalTime);


                    timeBtn.setText(localTime);

//                    int hour= calendar.get(Calendar.HOUR);
//                    int  minute=calendar.get(Calendar.MINUTE);
//                    int am_pm= calendar.get(Calendar.AM_PM);
//                    String AM_PM=TimePickerFragment.isAM_PM(am_pm);
//
//                    String time= hour+":"+minute +":"+AM_PM;


//                    Toast.makeText(MainActivity.this, "Time: "+time, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Local Time: " + localTime, Toast.LENGTH_SHORT).show();


                }
            };


    private void showDatePicker() {

        DatePickerFragment specificDate = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        //calender.setTimeInMillis(0);
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        specificDate.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        specificDate.setCallBack(ondate);
        android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();

        specificDate.show(manager, Constants.DateAndMonth.DATE_PICKER_STRING);


    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            monthOfYear++;
            String date = String.valueOf(year) + "-" + String.format("%02d", monthOfYear)
                    + "-" + String.format("%02d", dayOfMonth);


            dateBtn.setText(date);

        }
    };


}
