package com.colonsun.utils.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    private TimeUtil(){

    }

    public final static int YEAR = 1;
    public final static int MONTH = 2;
    public final static int WEEK = 3;
    public final static int DAY = 4;
    public final static int HOUR = 5;
    public final static int MINUTE = 6;
    public final static int SECOND = 7;

    public static String getFormatDate(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getCurrentDate(String format){
        return getFormatDate(new Date(),format);
    }

    public static String getPreDate(String format, int field, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        switch (field){
            case YEAR :
                calendar.add(Calendar.YEAR, amount);
                break;
            case MONTH :
                calendar.add(Calendar.MONTH, amount);
                break;
            case DAY :
                calendar.add(Calendar.DAY_OF_YEAR, amount);
                break;
            case HOUR :
                calendar.add(Calendar.HOUR, amount);
                break;
            case MINUTE :
                calendar.add(Calendar.MINUTE, amount);
                break;
            case SECOND :
                calendar.add(Calendar.SECOND, amount);
                break;
            default :
                throw new IllegalArgumentException();
        }
        return getFormatDate(calendar.getTime(), format);
    }

    public static String getPreDateByDay(String format, int day){

        return getPreDate(format, DAY, day);
    }

    public static String getPreDateByMonth(String format, int month){

        return getPreDate(format, MONTH, month);
    }

    public static String getPreDateByYear(String format, int year){
        return getPreDate(format, YEAR, year);
    }

    public static String getFirstDayOfField(String format, int field){
        Calendar calendar = Calendar.getInstance();
        if(field == YEAR){
            calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        }else if(field == MONTH){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        }else if(field == WEEK){
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
        }else{
            throw new IllegalArgumentException();
        }
        return getFormatDate(calendar.getTime(), format);
    }

    public static String getLastDayOfField(String format, int field){
        Calendar calendar = Calendar.getInstance();
        if(field == YEAR){
            calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        }else if(field == MONTH){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }else if(field == WEEK){
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
        }else{
            throw new IllegalArgumentException();
        }
        return getFormatDate(calendar.getTime(), format);
    }

}
