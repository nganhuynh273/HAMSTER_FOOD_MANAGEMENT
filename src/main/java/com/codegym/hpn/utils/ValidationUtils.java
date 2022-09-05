package com.codegym.hpn.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ValidationUtils {
    public static final String FULL_NAME_REGEX = "[A-Za-z\\s]+";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9_]{7,19}$";
    public static final String INTEGER_REGEX = "\\d+";
    public static final String DOUBLE_REGEX = "[\\d.]+";
    public static String minProductionDate;
    public static String maxExpirationDate;
    public static String minDateOfBirthValid;
    public static String maxDateOfBirthValid;

    public static boolean checkProductionDate(LocalDate inputDate) {
        int currentYear = LocalDate.now().getYear();
        int validRange = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String minDateString = "01/01/" + (currentYear - validRange);
        LocalDate minDate = LocalDate.parse(minDateString, formatter);
        minProductionDate = minDateString;
        return (inputDate.isAfter(minDate) || inputDate.isEqual(minDate)) && inputDate.isBefore(LocalDate.now());
    }

    public static boolean checkExpirationDate(LocalDate inputDate) {
        int currentYear = LocalDate.now().getYear();
        int validRange = 3;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String maxDateString = "31/12/" + (currentYear + validRange);
        LocalDate maxDate = LocalDate.parse(maxDateString, formatter);
        maxExpirationDate = maxDateString;
        return (inputDate.isBefore(maxDate) || inputDate.isEqual(maxDate)) && inputDate.isAfter(LocalDate.now());
    }

    public static boolean checkDateOfBirth(Date inputDate) {
        int currentYear = LocalDate.now().getYear();
        int validRangeMin = 90;
        int validRangeMax = 10;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String maxDateString = "31/12/" + (currentYear - validRangeMax);
        String minDateString = "01/01/" + (currentYear - validRangeMin);

        LocalDate minDateOfBirth = LocalDate.parse(minDateString,formatter);
        LocalDate maxDateOfBirth = LocalDate.parse(maxDateString,formatter);

        minDateOfBirthValid = minDateString;
        maxDateOfBirthValid = maxDateString;
        LocalDate dateOfBirth = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return (dateOfBirth.isAfter(minDateOfBirth) || dateOfBirth.isEqual(minDateOfBirth)) &&
                (dateOfBirth.isBefore(maxDateOfBirth) ||dateOfBirth.isEqual(maxDateOfBirth));
    }

}
