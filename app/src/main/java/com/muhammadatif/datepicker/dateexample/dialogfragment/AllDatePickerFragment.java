package com.muhammadatif.datepicker.dateexample.dialogfragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Atif Arif on 9/19/2017.
 */

public class AllDatePickerFragment extends DialogFragment {

    DatePickerDialog.OnDateSetListener onDateSet;

    private int year, month, day;

    public AllDatePickerFragment() {
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener onDate) {
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
        return new DatePickerDialog(getActivity(), onDateSet, year, month, day);
    }
}
