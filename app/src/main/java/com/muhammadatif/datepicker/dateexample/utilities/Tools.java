package com.muhammadatif.datepicker.dateexample.utilities;

import com.muhammadatif.datepicker.dateexample.globals.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Atif Arif on 9/19/2017.
 */

public class Tools {


    public static String getMinDate(String dateString, int days) throws ParseException {

        Date date = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT, Locale.ENGLISH).parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        Date newDate = calendar.getTime();
        calendar.setTime(newDate);
        String monthOfYear = new SimpleDateFormat(Constants.DateAndMonth.FULL_MONTH_NAME).format(calendar.getTime());//MMM for short Name(Jun)
        String dayOfMonth = new SimpleDateFormat(Constants.DateAndMonth.DATE_OF_MONTH).format(calendar.getTime());//Date Number 01
        String year = new SimpleDateFormat(Constants.DateAndMonth.YEAR).format(calendar.getTime());//year
        String date_string = year + "-" + monthOfYear + "-" + dayOfMonth;
        return date_string;
    }


    public static String getCurrentDate() throws ParseException {

        SimpleDateFormat date = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT);
        String currentDate = date.format(new Date());

        return currentDate;
    }


    public static String getMonthNameAndDateFormat(String date) throws ParseException {

        // Date Format "2017-10-01 00:00:00"
        Date d = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT, Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat(Constants.DateAndMonth.FULL_MONTH_NAME).format(cal.getTime());//MMM for short Name(Jun)
        String dateNumber = new SimpleDateFormat(Constants.DateAndMonth.DATE_OF_MONTH).format(cal.getTime());//Date Number 01
        String dateMonth = dateNumber + " " + monthName;
        return dateMonth; //01 october
    }

}
