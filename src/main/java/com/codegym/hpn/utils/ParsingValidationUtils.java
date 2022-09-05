package com.codegym.hpn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ParsingValidationUtils {
    public static boolean isLongParsable(String number) {
        try {
            Long.parseLong(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isIntParsable(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDecimalParsable(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDateParsableType1(String date) {
        try {
            new SimpleDateFormat("MM/dd/yyyy").parse(date);
            return true;
        } catch (final ParseException e) {
            return false;
        }
    }

    public static boolean isDateParsableType2(String date) {
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return true;
        } catch (final ParseException e) {
            return false;
        }
    }

    public static boolean isParsableToLocalDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

}
