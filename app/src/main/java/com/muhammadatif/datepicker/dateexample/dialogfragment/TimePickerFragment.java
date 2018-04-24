package com.muhammadatif.datepicker.dateexample.dialogfragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.TimePicker;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by Atif Arif on 1/17/2018.
 */

public class TimePickerFragment extends android.support.v4.app.DialogFragment {


    private TimePicker timePicker;

    TimePickerDialog.OnTimeSetListener onTimeSetListener;


    private int hour;
    private int minute;
    int amPm;


    TimePickerDialog timePickerDialog;

    public TimePickerFragment() {

    }

    public void setCallBack(TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        this.onTimeSetListener = onTimeSetListener;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);//HOUR_OF_DAY
        minute = c.get(Calendar.MINUTE);
        amPm = c.get(Calendar.AM_PM);
        // set time picker as current time
        timePickerDialog = new TimePickerDialog(getActivity(),
                onTimeSetListener, hour, minute, false);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            timePickerDialog.setTitle("");//Prevent Date picker from creating extra Title.!
        }

        return timePickerDialog;

    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    // set current time into textview
//                    tvDisplayTime.setText(new StringBuilder().append(pad(hour))
//                            .append(":").append(pad(minute)));

                    // set current time into timepicker
                    timePicker.setCurrentHour(hour);
                    timePicker.setCurrentMinute(minute);

                }
            };

    public static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    public static String isAM_PM(int value) {

        if (value == 0) {
            return "AM";
        } else {
            return "PM";
        }

    }

}
