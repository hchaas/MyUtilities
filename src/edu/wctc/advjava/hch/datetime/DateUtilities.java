package edu.wctc.advjava.hch.datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * This class is responsible for converting dates to strings, strings to dates,
 * and doing arithmetic between dates.
 * <p>
 * <b>Revision History</b>
 * <br>
 * <ul>
 * <li>2017-11-6: added javaDoc comments</li>
 * </ul>
 *
 * @author Haley C. Haas, hhaas@wctc.edu
 * @version 1.0
 * @since 1.8
 *
 */
public class DateUtilities {

    enum variableUnitOfTime {
        MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS;
    }
    
    enum unitOfTimeReturnType{
        MINUTES, HOURS, DAYS;
    }

//    public static void main(String[] args) {
//        DateUtilities sample = new DateUtilities();
//        LocalDate today = LocalDate.now();
//        String dateString = "10/31/17";
//        //sample.convertDateToStringDefault(today);
//        //sample.findDatePlusXWeeks(today, 4);
//
//    }
    /**
     * this method converts a date object to a string using a default format;
     * does not allow user input for format
     *
     * @param date - LocalDate object
     * @return String of a date
     */
    public String convertDateToStringDefault(LocalDate date) throws IllegalArgumentException {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        String strDate = date.format(format);
        //System.out.println("Date with default formatting: " + strDate);
        
        return strDate;
    }

    /**
     * this method converts a date object to a string using a format that the
     * user passes i
     *
     * @param date - LocalDate object
     * @param formatString - format string for how date should be formatted
     * @return String of a date that has been formatted per the format string parameter
     */
    public String convertDateToStringCustom(LocalDate date, String formatString) throws IllegalArgumentException {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        if (formatString == null || formatString.isEmpty()) {
            throw new IllegalArgumentException("Format string cannot be null or empty.");
        }
        try{
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatString);
        String strDate = date.format(format);
        //System.out.println("Date with user-defined format: " + strDate);
        return strDate;
        }
        catch (IllegalArgumentException iae){
            throw new IllegalArgumentException("Format string could not be parsed.");
        }

    }

    /**
     * this method converts a string to a date with a default forma
     *
     * @param stringDate - string of a date that will be parsed into an object
     * @return LocalDate object that has been parsed from the provided String parameter
     */
    public LocalDate convertStringToDateTimeSimple(String stringDate) throws IllegalArgumentException {
        if (stringDate == null || stringDate.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty or null.");
        }
        try {
            LocalDate date = LocalDate.parse(stringDate);
            //System.out.println(date);
            return date;
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("Date cannot be parsed.");
        }

    }

    /**
     * this method converts a string to a date with a defined format
     *
     * @param stringDate - string of a date that will be parsed into an object
     * @param formatString - format string for which the date should be
     * formatted according to
     * @return LocalDate object that has been parsed from the provided String parameter and format string
     */
    public LocalDate convertStringToDateTimeCustomFormat(String stringDate, String formatString) throws IllegalArgumentException {
        if (stringDate == null || stringDate.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty or null.");
        }
        if (formatString == null || formatString.isEmpty()) {
            throw new IllegalArgumentException("Format string cannot be empty or null.");
        }
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(formatString);
            LocalDate date = LocalDate.parse(stringDate, format);
            //System.out.println(date);
            return date;
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("Date format cannot be parsed.");
        }
    }

    /**
     * this method finds the difference between two provided dates and returns it in the unit of time specified by the
     * enum passed in a parameter
     *
     * 
     * @param date1 - first date from which you want to find the difference in time
     * @param date2 - second date from which you want to find the difference in time
     * @param returnUnit - enum unit of time you'd like the difference in dates returned as
     * @return long representing the difference in the two dates, provided in the unit specified in the returnUnit parameter
     */
    public long differenceBetweenTwoDates(LocalDateTime date1, LocalDateTime date2, unitOfTimeReturnType returnUnit) 
            throws IllegalArgumentException {

        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        if (returnUnit == null){
            throw new IllegalArgumentException("Unit of time return type cannot be null.");
        }
        
        Duration dayDifference = Duration.between(date1, date2);

        long min = dayDifference.toMinutes();
        long hrs = dayDifference.toHours();
        long days = dayDifference.toDays();
        
        switch(returnUnit){
            case MINUTES:
                return min;
            case HOURS: 
                return hrs;
            case DAYS: 
                return days;  
            default:
                throw new IllegalArgumentException("Unit of time return type invalid.");
        }
        
    }

    /**
     * this method finds the value of a date plus x unit of time; both the unit and the value of time
     * is defined in parameters
     *
     * @param date - LocalDate object to use as a starting date
     * @param timeUnit - variableUnitOfTime enum that tells the method what unit of time you'd like to add to the date
     * @param time - int amount of unit corresponding to the unit of time
     */
    public void findDatePlusXTime(LocalDate date, variableUnitOfTime timeUnit, int time) throws IllegalArgumentException {

        if (timeUnit == null) {
            throw new IllegalArgumentException("Unit of time cannot be null.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        LocalDate newDate = date;

        switch (timeUnit) {
            case HOURS:
                newDate = date.plus(time, ChronoUnit.HOURS);
                break;
            case DAYS:
                newDate = date.plus(time, ChronoUnit.DAYS);
                break;
            case WEEKS:
                newDate = date.plus(time, ChronoUnit.WEEKS);
                break;
            case MONTHS:
                newDate = date.plus(time, ChronoUnit.MONTHS);
                break;
            case YEARS:
                newDate = date.plus(time, ChronoUnit.YEARS);
        }

    }
}
