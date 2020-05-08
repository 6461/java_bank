package com.mma.bank;

public class Validator {
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
}
