package com.mma.bank;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {

    public static long moneyToLong(String str) {
        long value = 0;

        try {
            double decimal = Double.parseDouble(str) * 100;
            value = (long) decimal;
        } catch (NumberFormatException e) {
            value = 0;
        }

        return value;
    }

    public static String moneyToString(long amount) {
        double decimal = ((double) amount) / 100;

        return String.format("%.2f", decimal);
    }

    public static Date stringToDate(String text, String dateFormat) {
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

    public static String dateToString(Date date, String dateFormat) {
        String dateString;

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        dateString = sdf.format(date);

        return dateString;
    }
}
