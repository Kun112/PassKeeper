package com.anhquanha.passkeeper.util;

import com.anhquanha.passkeeper.MainApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static DateUtil instance;
    private final DateFormat dateFormat;
    private final DateFormat timeFormat;
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    private DateUtil() {
        dateFormat = android.text.format.DateFormat.getDateFormat(MainApplication.getContext());
        timeFormat = android.text.format.DateFormat.getTimeFormat(MainApplication.getContext());
    }

    public static DateUtil getInstance() {
        if (instance == null) {
            instance = new DateUtil();
        }
        return instance;
    }

    public synchronized static String formatDateTime(long timestamp) {
//        Date currentDate = new Date(timestamp);
//        DateFormat df = new SimpleDateFormat("dd/MM/yy");
//        return df.format(currentDate);
        if(timestamp==0){
            return "";
        }
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date netDate = (new Date(timestamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }



    public synchronized static String getDateTime(long time) {
        Date date = new Date(time); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm "); // the format of your date
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }

    public static String getCurrentDateWithFormat(String format) {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(c.getTime());
    }

    public static String convertSecondsToHMmSs(long seconds) {
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        return String.format("%02dH%02d", h, m);
    }

    public static long convertDateToTimeStamp(String dateFormat){
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        long milliseconds = 0;
        try {
            Date d = f.parse(dateFormat);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }
}