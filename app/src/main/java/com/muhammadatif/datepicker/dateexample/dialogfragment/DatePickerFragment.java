package com.muhammadatif.datepicker.dateexample.dialogfragment;

/**
 * Created by Atif Arif on 9/19/2017.
 */

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.muhammadatif.datepicker.dateexample.globals.Constants;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    OnDateSetListener onDateSet;

    private int year, month, day;

    private DatePickerDialog datePickerDialog;

    public DatePickerFragment() {
    }

    public void setCallBack(OnDateSetListener onDate) {
        this.onDateSet = onDate;
    }


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(), onDateSet,
                year, month, day);

        // calendar.add(Calendar.DATE, Constants.DateAndMonth.CURRENT_DAY);//Current day
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        // Set the Calendar new date as maximum date of date picker
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        // Subtract 90 days from Calendar updated date
        calendar.add(Calendar.DATE, -Constants.DateAndMonth.LAST_NINETY_DAY);

        // Set the Calendar new date as minimum date of date picker
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            datePickerDialog.setTitle("");//Prevent Date picker from creating extra Title.!
        }

        return datePickerDialog;

    }
}