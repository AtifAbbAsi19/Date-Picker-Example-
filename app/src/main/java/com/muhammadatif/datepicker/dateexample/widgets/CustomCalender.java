package com.muhammadatif.datepicker.dateexample.widgets;

import android.app.DatePickerDialog;
import android.support.v4.content.ContextCompat;
import android.widget.DatePicker;

import com.muhammadatif.datepicker.dateexample.R;
import com.muhammadatif.datepicker.dateexample.globals.Constants;
import com.muhammadatif.datepicker.dateexample.utilities.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Atif Arif on 10/7/2017.
 */

public class CustomCalender implements DatePickerDialog.OnDateSetListener {
    TextViewNormal mTextView;
    DatePickerDialog.OnDateSetListener onDate;


    public CustomCalender(TextViewNormal textViewNormal, DatePickerDialog.OnDateSetListener onDate) {
        mTextView = textViewNormal;
        this.onDate = onDate;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, dayOfMonth);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT);
        String formattedDate = sdf.format(c.getTime());
        try {
            mTextView.setText(Tools.changeDateFormatForTextView(formattedDate));
            mTextView.setTextColor(ContextCompat.getColor(view.getContext(), R.color.text_date_color));
            onDate.onDateSet(view, year, month, dayOfMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}