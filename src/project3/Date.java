package project3;

import java.util.StringTokenizer;
import java.util.Calendar;

/**
 * This class represents the date based off of the Gregorian Calendar
 *
 * @author David Ha, Andrew McAvoy
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constants for isValid method
     */
    final static int JANUARY = 1;
    final static int MARCH = 3;
    final static int APRIL = 4;
    final static int MAY = 5;
    final static int JUNE = 6;
    final static int JULY = 7;
    final static int AUGUST = 8;
    final static int SEPTEMBER = 9;
    final static int OCTOBER = 10;
    final static int NOVEMBER = 11;
    final static int DECEMBER = 12;
    final static int YEAR_LOWER_LIMIT = 1900;
    final static int THIRTY_DAY_MONTH = 30;
    final static int THIRTYONE_DAY_MONTH = 31;
    final static int FEB_NONLEAPYEAR = 28;
    final static int FEB_LEAPYEAR = 29;
    final static int TWO_DIGITS = 10;
    final static int LESS = -1;
    final static int EQUAL = 0;
    final static int GREATER = 1;

    /**
     * Constants for the isLeapYear method
     */
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;

    /**
     * Constructor for the date
     *
     * @param date takes in the date with format mm/dd/yyyy and creates Date object
     */
    public Date(String date) {
        StringTokenizer tokenizer = new StringTokenizer(date, "/");
        month = Integer.parseInt(tokenizer.nextToken());
        day = Integer.parseInt(tokenizer.nextToken());
        year = Integer.parseInt(tokenizer.nextToken());
    }

    /**
     * Default constructor for the date using date stored in system
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1; //Add 1 to correct for Calendar class Zero-Indexing
        this.day = calendar.get(Calendar.DATE);
    }

    /**
     * Checks if the date is valid
     *
     * @return boolean value True if date if valid False if otherwise
     */
    public boolean isValid() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DATE);
        if ((year == currentYear) && (month > currentMonth)) {
            return false;
        } else if ((year == currentYear) && (currentMonth == month)) {
            if (day > currentDay) {
                return false;
            }
        }
        if ((year < YEAR_LOWER_LIMIT) || (year > currentYear)) {
            return false;
        }
        if ((month < JANUARY) || (month > DECEMBER)) {
            return false;
        }
        if ((month == JANUARY) || (month == MARCH) || (month == MAY) ||
                (month == JULY) || (month == AUGUST) || (month == OCTOBER) || (month == DECEMBER)) {
            if ((day <= 0) || (day > THIRTYONE_DAY_MONTH)) {
                return false;
            }
        } else if ((month == APRIL) || (month == JUNE) || (month == SEPTEMBER) || (month == NOVEMBER)) {
            if ((day <= 0) || (day > THIRTY_DAY_MONTH)) {
                return false;
            }
        } else {
            if (isLeapYear(year)) {
                if ((day <= 0) || (day > FEB_LEAPYEAR)) {
                    return false;
                }
            } else if ((day <= 0) || (day > FEB_NONLEAPYEAR)) { //Assumes isLeapYear has returned false
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if given year is a leap year
     *
     * @param year input from isValid method
     * @return boolean value True if year is a leap year False if otherwise
     */
    private static boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUARTERCENTENNIAL == 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method for year
     *
     * @return value of year
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter method for month
     *
     * @return value of month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getter method for day
     *
     * @return value of day
     */
    public int getDay() {
        return day;
    }

    /**
     * Compares the value of two dates
     *
     * @param date Date to be compared with the value that is calling this method
     * @return 1 0 or -1 depending on the comparison of the two dates
     */
    @Override
    public int compareTo(Date date) {
        if (year < date.year) {
            return LESS;
        } else if (year == date.year) {
            if (month < date.month) {
                return LESS;
            } else if (month == date.month) {
                if (day < date.day) {
                    return LESS;
                } else if (day == date.day) {
                    return EQUAL;
                }
            }
        }
        return GREATER;
    }

    /**
     * Turns date into a String in the following format mm/dd/yyyy
     *
     * @return date in String form
     */
    @Override
    public String toString() {
        String dateToString = "";
        if (month < TWO_DIGITS) { //adds zero in front of month if less than 10 or two digits
            dateToString += "0" + month + "/";
        } else {
            dateToString += month + "/";
        }
        if (day < TWO_DIGITS) {//adds zero in front of day if less than 10 or two digits
            dateToString += "0" + day + "/";
        } else {
            dateToString += day + "/";
        }
        return dateToString + year;
    }
}
