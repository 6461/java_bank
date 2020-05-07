package com.mma.bank;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {

    public static Date parseDate(String text, String dateFormat) {
        Date date;

        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            date = df.parse(text);
        } catch (ParseException ex) {
            date = null;
        }

        return date;
    }

    public static String getString(Date date, String dateFormat) {
        String dateString;

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        dateString = sdf.format(date);

        return dateString;
    }
}
