package com.muhammadatif.datepicker.dateexample;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.muhammadatif.datepicker.dateexample.dialogfragment.AllDatePickerFragment;
import com.muhammadatif.datepicker.dateexample.dialogfragment.DatePickerFragment;
import com.muhammadatif.datepicker.dateexample.globals.Constants;
import com.muhammadatif.datepicker.dateexample.utilities.Tools;

import java.text.ParseException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    TextView date, monthDate, ninetyDays;
    Button specificDateBtn, allDateBtn;

    String DATESTRING = "2017-10-01 00:00:00";
    String monthName;

    int getDate = 7;
    int getMonth = 30;
    int getNinetyDays = 90;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        try {
            date.setText(Tools.getMonthNameAndDateFormat(DATESTRING));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (getDate == 7) {
            try {
                DATESTRING = "2017-09-20 00:00:00";

                date.setText("last Seven Days::" + Tools.getMinDate(DATESTRING, Constants.DateAndMonth.LAST_SEVEN_DAY));
                // Toast.makeText(MainActivity.this, "Last Seven Days"+getLastSevenDays(DATESTRING), Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (getMonth == 30) {
            try {
                DATESTRING = "2017-09-30 00:00:00";

                monthDate.setText("last 30 Days::" + Tools.getMinDate(DATESTRING, Constants.DateAndMonth.LAST_THIRTY_DAY));
                // Toast.makeText(MainActivity.this, "Last Seven Days"+getLastSevenDays(DATESTRING), Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (getNinetyDays == 90) {
            try {
                DATESTRING = "2017-12-31 00:00:00";

                ninetyDays.setText("last 90 Days::" + Tools.getMinDate(DATESTRING, Constants.DateAndMonth.LAST_NINETY_DAY));
                // Toast.makeText(MainActivity.this, "Last Seven Days"+getLastSevenDays(DATESTRING), Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        specificDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(Constants.DateAndMonth.SHOW_ALL_STRING);
            }
        });

        allDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(Constants.DateAndMonth.SHOW_SPECIFIC_STRING);
            }
        });


    }

    private void init() {
        date = (TextView) findViewById(R.id.date);
        monthDate = (TextView) findViewById(R.id.dateMonth);
        ninetyDays = (TextView) findViewById(R.id.ninetyDays);
        specificDateBtn = (Button) findViewById(R.id.dateBtn);
        allDateBtn = (Button) findViewById(R.id.allDateBtn);

    }


    private void showDatePicker(String selectCalendar) {

        if (selectCalendar.equalsIgnoreCase(Constants.DateAndMonth.SHOW_SPECIFIC_STRING)) {

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
            specificDate.show(getSupportFragmentManager(),Constants.DateAndMonth.DATE_PICKER_STRING);

        } else {

            AllDatePickerFragment allDate = new AllDatePickerFragment();
            /**
             * Set Up Current Date Into dialog
             */
            Calendar calender = Calendar.getInstance();
            //calender.setTimeInMillis(0);
            Bundle args = new Bundle();
            args.putInt("year", calender.get(Calendar.YEAR));
            args.putInt("month", calender.get(Calendar.MONTH));
            args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
            allDate.setArguments(args);
            /**
             * Set Call back to capture selected date
             */
            allDate.setCallBack(ondate);
            allDate.show(getSupportFragmentManager(), Constants.DateAndMonth.DATE_PICKER_STRING);


        }


    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear++;
            Toast.makeText(
                    MainActivity.this,
                    String.valueOf(year) + "-" + String.format("%02d", monthOfYear)
                            + "-" + String.format("%02d", dayOfMonth),
                    Toast.LENGTH_LONG).show();
        }
    };


}
