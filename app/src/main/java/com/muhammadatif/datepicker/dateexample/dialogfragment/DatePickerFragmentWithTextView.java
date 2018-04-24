package com.muhammadatif.datepicker.dateexample.dialogfragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.muhammadatif.datepicker.dateexample.globals.Constants;
import com.muhammadatif.datepicker.dateexample.widgets.TextViewNormal;

import java.util.Calendar;

/**
 * Created by Atif Arif on 4/24/2018.
 */

public class DatePickerFragmentWithTextView extends DialogFragment {

//    OnDateSetListener onDateSet;

    TextViewNormal myTelenorTextViewNormal;

    private int year, month, day;

    private DatePickerDialog datePickerDialog;

    public DatePickerFragmentWithTextView() {
    }

    public void setSetTextView(TextViewNormal MyTelenorTextViewNormal) {
        this.myTelenorTextViewNormal = MyTelenorTextViewNormal;

    }
//
//    public void setCallBack(OnDateSetListener onDate) {
//        this.onDateSet = onDate;
//    }


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
            datePickerDialog.setTitle("");
        }
        datePickerDialog.setCustomTitle(null);

        return datePickerDialog;

    }


    DatePickerDialog.OnDateSetListener onDateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            monthOfYear++;
            String selectedDate = String.valueOf(year) + "-" + String.format("%02d", monthOfYear)
                    + "-" + String.format("%02d", dayOfMonth);

            myTelenorTextViewNormal.setError(null);
            myTelenorTextViewNormal.setText(selectedDate);
//            myTelenorTextViewNormal.setFocusable(true);
//            myTelenorTextViewNormal.requestFocus();


        }
    };


}