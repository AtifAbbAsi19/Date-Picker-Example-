package com.muhammadatif.datepicker.dateexample;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ActionProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.muhammadatif.datepicker.dateexample.application.AppClass;
import com.muhammadatif.datepicker.dateexample.dialogfragment.AllDatePickerFragment;
import com.muhammadatif.datepicker.dateexample.dialogfragment.DatePickerFragment;
import com.muhammadatif.datepicker.dateexample.globals.Constants;
import com.muhammadatif.datepicker.dateexample.utilities.Tools;
import com.muhammadatif.datepicker.dateexample.widgets.CustomCalender;
import com.muhammadatif.datepicker.dateexample.widgets.TextViewNormal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static android.provider.Settings.System.FONT_SCALE;

public class MainActivity extends AppCompatActivity {


    TextView date, monthDate, ninetyDays;
    Button specificDateBtn, allDateBtn;

    String SAMPLE_DATE_FORMAT_STRING = "2017-10-01 00:00:00";
    String SAMPLE_TIME_FORMAT_STRING = "2017-10-07 18:17:05";

    String dateOne, dateTwo;

    TextViewNormal tvDateOne, tvDateTwo, timeViewer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppClass.initializeCustomFonts(getAssets());

        init();
        UiListener();



      //  if (Objects.equals(location.get(i).get("campo_categoria").toString(),"Any other Choice"))



//        Settings.System.putFloat(getBaseContext().getContentResolver(),
//                Settings.System.FONT_SCALE, (float) 1.0);

        try {
            setDefaultDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            date.setText(Tools.getMonthNameAndDateFormat(SAMPLE_DATE_FORMAT_STRING));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            showDateAccordingToDays(Tools.getCurrentDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        showTime(SAMPLE_TIME_FORMAT_STRING);
    }

    private void showTime(String time) {
        try {
            timeViewer.setText(Tools.getTimeSecMinutesHoursDays(time));
            Toast.makeText(this, "" + Tools.getTimeSecMinutesHoursDays(time), Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showDateAccordingToDays(String SAMPLE_DATE_FORMAT_STRING) {
        if (Constants.DateAndMonth.LAST_SEVEN_DAY == 7) {
            try {
                //SAMPLE_DATE_FORMAT_STRING = "2017-09-20 00:00:00";

                date.setText("last Seven Days::" + Tools.getMinDate(SAMPLE_DATE_FORMAT_STRING, Constants.DateAndMonth.LAST_SEVEN_DAY));
                // Toast.makeText(MainActivity.this, "Last Seven Days"+getLastSevenDays(SAMPLE_DATE_FORMAT_STRING), Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (Constants.DateAndMonth.LAST_THIRTY_DAY == 30) {
            try {
                //SAMPLE_DATE_FORMAT_STRING = "2017-09-30 00:00:00";

                monthDate.setText("last 30 Days::" + Tools.getMinDate(SAMPLE_DATE_FORMAT_STRING, Constants.DateAndMonth.LAST_THIRTY_DAY));
                // Toast.makeText(MainActivity.this, "Last Seven Days"+getLastSevenDays(SAMPLE_DATE_FORMAT_STRING), Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (Constants.DateAndMonth.LAST_NINETY_DAY == 90) {
            try {
               // SAMPLE_DATE_FORMAT_STRING = "2017-12-31 00:00:00";

                ninetyDays.setText("last 90 Days::" + Tools.getMinDate(SAMPLE_DATE_FORMAT_STRING, Constants.DateAndMonth.LAST_NINETY_DAY));
                // Toast.makeText(MainActivity.this, "Last Seven Days"+getLastSevenDays(SAMPLE_DATE_FORMAT_STRING), Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    private void UiListener() {


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

        tvDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    showCalendar(tvDateOne, true);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        tvDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    showCalendar(tvDateTwo, false);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void init() {
        date = (TextView) findViewById(R.id.date);
        monthDate = (TextView) findViewById(R.id.dateMonth);
        ninetyDays = (TextView) findViewById(R.id.ninetyDays);
        tvDateOne = (TextViewNormal) findViewById(R.id.tvDateOne);
        tvDateTwo = (TextViewNormal) findViewById(R.id.tvDateTwo);
        timeViewer = (TextViewNormal) findViewById(R.id.timeViewer);
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
            specificDate.show(getSupportFragmentManager(), Constants.DateAndMonth.DATE_PICKER_STRING);

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

    private void setDefaultDate() throws ParseException {
        tvDateOne.setText(Tools.changeDateFormatForTextView(Tools.getCurrentDate()));
        tvDateTwo.setText(Tools.changeDateFormatForTextView(Tools.getCurrentDate()));
    }

    public void showCalendar(TextViewNormal textViewNormal, boolean isStartDateSelected) throws ParseException {

        dateOne = Tools.changeDateFormatForMethods(tvDateOne.getText().toString());
        dateTwo = Tools.changeDateFormatForMethods(tvDateTwo.getText().toString());

        Date minDate = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT).parse(dateOne);
        Date maxDate = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT).parse(dateTwo);

        CustomCalender customCalender;

        final Calendar calendar = Calendar.getInstance();
        if (isStartDateSelected) {
            calendar.setTime(minDate);
        } else {
            calendar.setTime(maxDate);
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        customCalender = new CustomCalender(textViewNormal, ondate);


        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, customCalender,
                year, month, day);
        //calculate min and max dates (for older versions use System Current TimeMillis

        if (isStartDateSelected) {

            Date dateMax = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT, Locale.ENGLISH).parse(dateTwo);
            calendar.setTime(dateMax);
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        } else {

            Date dateMin = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT, Locale.ENGLISH).parse(dateOne);
            calendar.setTime(dateMin);
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            datePickerDialog.setTitle("");//Prevent Date picker from creating extra Title.!
        }

        datePickerDialog.show();

    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            String date=String.valueOf(year) + "-" + String.format("%02d", monthOfYear)
                    + "-" + String.format("%02d", dayOfMonth);

            try {
                dateOne = Tools.changeDateFormatForMethods(tvDateOne.getText().toString());



                String dateTime=Tools.getDateWithSuffixAndMonthNameFormat(date,Constants.DateAndMonth.SHORT_MONTH_NAME);
                Toast.makeText(
                        MainActivity.this,
                        dateTime,
                        Toast.LENGTH_LONG).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                dateTwo = Tools.changeDateFormatForMethods(tvDateTwo.getText().toString());
                String dateTime=Tools.getDateWithSuffixAndMonthNameFormat(date,Constants.DateAndMonth.SHORT_MONTH_NAME);
                Toast.makeText(
                        MainActivity.this,
                        dateTime,
                        Toast.LENGTH_LONG).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            monthOfYear++;



            Toast.makeText(
                    MainActivity.this,
                    String.valueOf(year) + "-" + String.format("%02d", monthOfYear)
                            + "-" + String.format("%02d", dayOfMonth),
                    Toast.LENGTH_LONG).show();
        }
    };

    /** @hide */
    public static void getConfigurationForUser(ContentResolver cr,
                                               Configuration outConfig, int userHandle) {
        outConfig.fontScale = Settings.System.getFloat(
                cr, FONT_SCALE, outConfig.fontScale);
        if (outConfig.fontScale < 0) {
            outConfig.fontScale = 1;
        }
    }
}
