package com.stone.tasklist.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/1/6.
 */
public class DateUtils{

    //Calendar的月是从0开始的, 要对月份+1

    private static SimpleDateFormat fmt = new SimpleDateFormat();

    public static String convertCalendarToString(Calendar cal) {
        return getDateFromCalendar(cal);
    }

    private static String getDateFromCalendar(Calendar cal) {
        return cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +
                cal.get(Calendar.DAY_OF_MONTH);
    }

    public static String getOtherDate(Calendar cal, int days) {
        cal.add(Calendar.DAY_OF_MONTH, days);
        return getDateFromCalendar(cal);
    }

    public static String convertDateToString(Date date) {
        return fmt.format(date);
    }

}
