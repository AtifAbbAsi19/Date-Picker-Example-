package com.muhammadatif.datepicker.dateexample.utilities;

import android.content.ContentResolver;
import android.content.res.Configuration;
import android.provider.Settings;

import com.muhammadatif.datepicker.dateexample.globals.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.provider.Settings.System.FONT_SCALE;

/**
 * Created by Muhammad Atif Arif on 9/19/2017.
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

    public static long getMiliSecondsFromDate(String myDate) {
        long miliSec = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        miliSec = date.getTime();
        return miliSec;
    }

    public static int getDaysDifferenceBetweenMiliseconds(long startDate, long endDate) {

        long duration = endDate - startDate;

        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

        return (int) diffInDays;
    }


    public static long getCurrentMiliSeconds() {
        return System.currentTimeMillis();
    }


    public static String getLastDate(String dateString, int days) throws ParseException {

        Date date = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT, Locale.ENGLISH).parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        Date newDate = calendar.getTime();
        calendar.setTime(newDate);
//        String monthOfYear = new SimpleDateFormat(Constants.DateAndMonth.FULL_MONTH_NAME).format(calendar.getTime());//MMM for short Name(Jun)
//        String dayOfMonth = new SimpleDateFormat(Constants.DateAndMonth.DATE_OF_MONTH).format(calendar.getTime());//Date Number 01
//        String year = new SimpleDateFormat(Constants.DateAndMonth.YEAR).format(calendar.getTime());//year
        String date_string = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT).format(calendar.getTime());
        return date_string;
    }


    public static String getCurrentDate() throws ParseException {

        SimpleDateFormat date = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT);
        String currentDate = date.format(new Date());

        return currentDate;
    }


    public static String getMonthAndDateFormat(String date, String month) throws ParseException {
        String dateMonth = " ";

            // Date Format "2017-10-01 00:00:00"
            Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            String monthName = new SimpleDateFormat(month).format(cal.getTime());//MMM for short Name(Jun)
            String dateNumber = new SimpleDateFormat("dd").format(cal.getTime());//Date Number 01
            dateMonth = dateNumber + " " + monthName;
        return dateMonth;
    }

    //1st,2nd,3rd,4th, Feb
    public static String getDateWithSuffixAndMonthNameFormat(String date, String month) throws ParseException {

        // Date Format "2017-10-01 00:00:00"
        Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat(month).format(cal.getTime());//MMM for short Name(Jun)
        String dateNumber = new SimpleDateFormat("dd").format(cal.getTime());//Date Number 01
        String dateMonth = getDayNumberSuffix(Integer.valueOf(dateNumber)) + " " + monthName;
        return dateMonth;//4th Feb
    }

    public static String getDayNumberSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return day + "th";
        }
        switch (day % 10) {
            case 1:
                return day + "st";
            case 2:
                return day + "nd";
            case 3:
                return day + "rd";
            case 31:
                return day + "st";
            default:
                return day + "th";
        }
    }



    public static String calculateTime(long milis) {

        String time="";
        int day = (int) (milis / (1000 * 60 * 60 * 24));
        int hours = (int) ((milis - (1000 * 60 * 60 * 24 * day)) / (1000 * 60 * 60));
        int minute = (int) (milis - (1000 * 60 * 60 * 24 * day) - (1000 * 60 * 60 * hours)) / (1000 * 60);
        int second = (int) ((milis / 1000) % 60);

        if(day>0){
            if(day==1){
                time =day+" day";
            }else{
                time =day+" days";
            }
        }else if(day<1 && hours>0){

            if(hours==1){
                time =hours +" hr";
            }else{
                time =hours +" hrs";
            }
        }else if(hours<1 && minute>0){
            if(minute==1){
                time =minute +" min";
            }else{
                time =minute +" mins";
            }
        }else if(minute<1 && second>0){

            if(second==1){
                time =second +" sec";
            }else{
                time =second +" secs";
            }
        }else {

        if (second <= 0) {
            time = "0 sec";
        }
    }

        try {
            int timeUnitDay = (int) TimeUnit.MILLISECONDS.toDays(milis);
            long timeUnitHours = TimeUnit.MILLISECONDS.toHours(milis);
            long timeUnitMinutes = TimeUnit.MILLISECONDS.toMinutes(milis);
            long timeUnitSeconds = TimeUnit.MILLISECONDS.toSeconds(milis);
        } catch (Exception e) {
            e.printStackTrace();
        }



//        int day = (int)TimeUnit.SECONDS.toDays(seconds);
//        long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
//        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
//        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);

//        int day = (int) TimeUnit.SECONDS.toDays(seconds);
//        long hours = TimeUnit.SECONDS.toHours(seconds) -
//                TimeUnit.DAYS.toHours(day);
//        long minute = TimeUnit.SECONDS.toMinutes(seconds) -
//                TimeUnit.DAYS.toMinutes(day) -
//                TimeUnit.HOURS.toMinutes(hours);
//        long second = TimeUnit.SECONDS.toSeconds(seconds) -
//                TimeUnit.DAYS.toSeconds(day) -
//                TimeUnit.HOURS.toSeconds(hours) -
//                TimeUnit.MINUTES.toSeconds(minute);


        //  System.out.println("Day " + day + " Hour " + hours + " Minute " + minute + " Seconds " + second);

        // time = "Day " + day + " : Hour " + hours + " : Minute " + minute + " : Seconds " + second;
        return time;
    }

    //minutes:Hours:days
    public static String getTimeSecMinutesHoursDays(String dateString) throws ParseException {


        SimpleDateFormat endDateFormat = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_TIME_FORMAT);
        String currentDateAndTime = endDateFormat.format(new Date());
        Date endDate = endDateFormat.parse(currentDateAndTime);

        SimpleDateFormat startDateFormat = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_TIME_FORMAT);
        Date startDate = startDateFormat.parse(dateString);
        // format the java.util.Date object to the desired format
      //  String startDateString = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_TIME_FORMAT).format(startDate);
//        long startMili = Tools.getMiliSecondsFromDateANDTIME(startDateString);
//        long endMili = Tools.getMiliSecondsFromDateANDTIME(currentDateAndTime);
        long difference = endDate.getTime() - startDate.getTime();

//        String[] separated = minutesHoursDays.split(":");
//        long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);

        String minutesHoursDays=calculateTime(difference); //minutes:Hours:days

        return minutesHoursDays;

    }

    public static long getMiliSecondsFromDateANDTIME(String myDate) {
        long miliSec = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_TIME_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        miliSec = date.getTime();
        return miliSec;
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date firstOfMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);

        return cal.getTime();
    }


    public static String getDateString(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormatter.format(date);
    }


    public static Date getDateFromLiterals(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return c.getTime();
    }


    public static Date dateFromString(String date) {
        Date convertedDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        try {
            convertedDate = format.parse(date);
        } catch (Exception e) {
        }

        return convertedDate;
    }


    public static int daysBetweenDates(Date date1, Date date2) {
        //milliseconds
        long different = date2.getTime() - date1.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;

        return (int) elapsedDays;
    }


    public static void calculateTimeSimpleMethod(long seconds) {
        long sec = seconds % 60;
        long minutes = seconds % 3600 / 60;
        long hours = seconds % 86400 / 3600;
        long days = seconds / 86400;

        System.out.println("Day " + days + " Hour " + hours + " Minute " + minutes + " Seconds " + sec);
    }

    //22-02-2017 to 22 Feb 2017
    public static String changeDateFormatForTextView(String dateString) throws ParseException {

        // parse the String "29-07-2013" to a java.util.Date object
        Date date = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT).parse(dateString);
        // format the java.util.Date object to the desired format
        String formattedDate = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT_SHORT_NAME).format(date);

        return formattedDate;
    }


    //22-02-2017 to 22-02-17
    public static String getDateAccordingToDesireFormat(String dateString) throws ParseException {

        // parse the String "29-07-2013" to a java.util.Date object
        Date date = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT).parse(dateString);
        // format the java.util.Date object to the desired format
        String formattedDate = new SimpleDateFormat(Constants.DateAndMonth.NEW_SAMPLE_DATE_FORMAT).format(date);

        return formattedDate;
    }

    public static String changeDateFormatForMethods(String dateString) throws ParseException {

        // parse the String "29-07-2013" to a java.util.Date object //"dd MMM yyyy"
        Date date = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT_SHORT_NAME).parse(dateString); //SAMPLE_DATE_FORMAT_SHORT_NAME
        // format the java.util.Date object to the desired format
        String formattedDate = new SimpleDateFormat(Constants.DateAndMonth.SAMPLE_DATE_FORMAT).format(date);

        return formattedDate;
    }


    public static String getDurationBreakdown(long millis)
    {
        if(millis < 0)
        {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        sb.append(days);
        sb.append(" Days ");
        sb.append(hours);
        sb.append(" Hours ");
        sb.append(minutes);
        sb.append(" Minutes ");
        sb.append(seconds);
        sb.append(" Seconds");

        return(sb.toString());
    }


//    /** @hide */
//    public static void getConfigurationForUser(ContentResolver cr,
//                                               Configuration outConfig, int userHandle) {
//        outConfig.fontScale = Settings.System.getFloatForUser(
//                cr, FONT_SCALE, outConfig.fontScale, userHandle);
//        if (outConfig.fontScale < 0) {
//            outConfig.fontScale = 1;
//        }
//    }

}
