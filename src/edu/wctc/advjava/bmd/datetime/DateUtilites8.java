/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.advjava.bmd.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * A Date Utilities helper class that provides extra LocalDateTime functionality that is 
 * either not provided by the original API or provides simplified method naming. 
 * 
 * @author Brandon Michael Dopp
 * @version 1.0
 * @since 1.8
 */
public class DateUtilites8 {

    private static final int BREAK_DOWN_UNITS = 6;
    public static final int YEARS_IN_ARRAY = 0;
    public static final int MONTHS_IN_ARRAY = 1;
    public static final int DAYS_IN_ARRAY = 2;
    public static final int HOURS_IN_ARRAY = 3;
    public static final int MINNUTES_IN_ARRAY = 4;
    public static final int SECONDS_IN_ARRAY = 5;
    
    public static final String SHORT_DATE_ONLY = "MM/dd/yyyy";
    public static final String LONG_DATE_ONLY = "MMMM dd, yyyy";
    public static final String SHORT_TIME_ONLY = "hh:mm a";
    public static final String LONG_TIME_ONLY = "HH:mm:ss";
    public static final String SHORT_DATE_TIME = SHORT_DATE_ONLY + " " + SHORT_TIME_ONLY;
    public static final String LONG_DATE_TIME = LONG_DATE_ONLY + " " + LONG_TIME_ONLY;
    
    
    private String defaultPattern;
    
    public DateUtilites8(){
        defaultPattern = SHORT_DATE_TIME;
    }
        
    /**
     * Takes in two LocalDateTime Objects and a TemporalUnit and calculates the value between them.
     * Method simply preforms fromDate.until(toDate,unit) while insuring fromDate.isAfter(toDate)
     * and swaps arguments if condition is false.
     * 
     * @param fromDate first Date Object
     * @param toDate second Date Object
     * @param unit The TemporalUnit to be calculated from date objects
     * @return a long value that is representative of the TemproalUnit between fromDate - toDate
     * @throws UnsupportedTemporalTypeException if the unit is not supported
     * @throws ArithmeticException if numeric overflow occurs
     * @throws IllegalArgumentException Thrown if any of the arguments are null
     */
    public final long getTemporalUnitBetween(LocalDateTime fromDate, LocalDateTime toDate, TemporalUnit unit)
                throws UnsupportedTemporalTypeException,ArithmeticException,IllegalArgumentException {
       if(fromDate == null || toDate == null || unit == null) throw new IllegalArgumentException("Arguments May Not Be Null");
       if(fromDate.isAfter(toDate)){LocalDateTime swap = fromDate;fromDate = toDate;toDate = swap;}//Swap Dates
       return fromDate.until(toDate, unit);
     }
    
    /**
     * Method calls getTemporalUnitBetween(fromDate,toDate,unit) with ChrnoUnit.YEARS argument for unit
     * 
     * @param date1 first date Object
     * @param date2 second date Object
     * @return a long that represent the years between date1 and date2
     * @throws ArithmeticException if numeric overflow occurs
     * @throws IllegalArgumentException Thrown if any of the arguments are null
     */
    public final long getYearsBetween(LocalDateTime date1, LocalDateTime date2)
                throws ArithmeticException,IllegalArgumentException {
       return getTemporalUnitBetween(date1,date2,ChronoUnit.YEARS);
    }
    
     /**
     * Method calls getTemporalUnitBetween(fromDate,toDate,unit) with ChrnoUnit.MONTHS argument for unit
     * 
     * @param date1 first date Object
     * @param date2 second date Object
     * @return a long that represent the months between date1 and date2
     * @throws ArithmeticException if numeric overflow occurs
     * @throws IllegalArgumentException Thrown if any of the arguments are null
     */
    public final long getMonthsBetween(LocalDateTime date1, LocalDateTime date2)
                throws ArithmeticException,IllegalArgumentException {
       return getTemporalUnitBetween(date1,date2,ChronoUnit.MONTHS);
    }
    
     /**
     * Method calls getTemporalUnitBetween(fromDate,toDate,unit) with ChrnoUnit.DAYS argument for unit
     * 
     * @param date1 first date Object
     * @param date2 second date Object
     * @return a long that represent the days between date1 and date2
     * @throws ArithmeticException if numeric overflow occurs
     * @throws IllegalArgumentException Thrown if any of the arguments are null
     */
    public final long getDaysBetween(LocalDateTime date1, LocalDateTime date2)
                throws ArithmeticException,IllegalArgumentException {
       return getTemporalUnitBetween(date1,date2,ChronoUnit.DAYS);
    }
    
     /**
     * Method calls getTemporalUnitBetween(fromDate,toDate,unit) with ChrnoUnit.MINUTES argument for unit
     * 
     * @param date1 first date Object
     * @param date2 second date Object
     * @return a long that represent the minutes between date1 and date2
     * @throws ArithmeticException if numeric overflow occurs
     * @throws IllegalArgumentException Thrown if any of the arguments are null
     */
    public final long getMinutesBetween(LocalDateTime date1, LocalDateTime date2)
                throws ArithmeticException,IllegalArgumentException {
       return getTemporalUnitBetween(date1,date2,ChronoUnit.MINUTES);
    }
    
    /**
     * Method calls getTemporalUnitBetween(fromDate,toDate,unit) with ChrnoUnit.HOURS argument for unit
     * 
     * @param date1 first date Object
     * @param date2 second date Object
     * @return a long that represent the hours between date1 and date2
     * @throws ArithmeticException if numeric overflow occurs
     * @throws IllegalArgumentException Thrown if any of the arguments are null
     */
    public final long getHoursBetween(LocalDateTime date1, LocalDateTime date2)
                throws ArithmeticException,IllegalArgumentException {
       return getTemporalUnitBetween(date1,date2,ChronoUnit.HOURS);
    }
    
    
    /**
     * Calculates the years,months,days,hours,minutes, and seconds in time between two LocalDateTime objects where each value is broken down into an array<br>
     * Example:<br>
     * <b>date1</b> = 4/29/1992 7:30:00am<br>
     * <b>date2</b> = 11/6/2017 9:00:00pm<br>
     * the return value would be an array: {25,6,8,13,30,0} <br>
     * meaning: 25 Years, 6 Months, 8 Days, 13 Hours, 30 Minutes, 0 Seconds has passed between date1 and date2
     * 
     * @param date1 first date Object
     * @param date2 second date Object
     * @return An array of long values where each value represents the additive: 
     * <ol start="0">
     * <li>YEARS</li>
     * <li>MONTHS</li>
     * <li>DAYS</li>
     * <li>HOURS</li>
     * <li>MINUTES</li>
     * <li>SECONDS</li>
     * </ol>
     * between date1 and date2. <br>
     * @throws ArithmeticException if numeric overflow occurs
     * @throws IllegalArgumentException Thrown if any of the arguments are null
     */
    public final long[] getChronoUnitBreakDownBetween(LocalDateTime date1, LocalDateTime date2)
            throws ArithmeticException,IllegalArgumentException{
       if(date1 == null || date2 == null) throw new IllegalArgumentException("Arguments May Not Be Null");
       if(date1.isAfter(date2)){LocalDateTime swap = date1;date1 = date2;date2 = swap;}//Swap Dates
        System.out.println();
       long[] breakDown = new long[BREAK_DOWN_UNITS];
       LocalDateTime temp = LocalDateTime.from(date1);
       
       breakDown[YEARS_IN_ARRAY] = ChronoUnit.YEARS.between(temp, date2);
       temp = temp.plusYears(breakDown[YEARS_IN_ARRAY]);
       
       breakDown[MONTHS_IN_ARRAY] = ChronoUnit.MONTHS.between(temp, date2);
       temp = temp.plusMonths(breakDown[MONTHS_IN_ARRAY]);
       
       breakDown[DAYS_IN_ARRAY] = ChronoUnit.DAYS.between(temp, date2);
       temp = temp.plusDays(breakDown[DAYS_IN_ARRAY]);
       
       breakDown[HOURS_IN_ARRAY] = ChronoUnit.HOURS.between(temp, date2);
       temp = temp.plusHours(breakDown[HOURS_IN_ARRAY]);
              
       breakDown[MINNUTES_IN_ARRAY] = ChronoUnit.MINUTES.between(temp, date2);
       temp = temp.plusMinutes(breakDown[MINNUTES_IN_ARRAY]);
       
       breakDown[SECONDS_IN_ARRAY] = ChronoUnit.SECONDS.between(temp, date2);
       temp = temp.plusSeconds(breakDown[SECONDS_IN_ARRAY]);
       
       return breakDown;
    }
    
    /**
     * Calculates the years,months,days,hours,minutes, and seconds in time between two LocalDateTime objects and outputs results as a formated string<br>
     * Example:<br>
     * <b>date1</b> = 4/29/1992 7:30:00am<br>
     * <b>date2</b> = 11/6/2017 9:00:00pm<br>
     * would return "25 Years, 6 Months, 8 Days, 13 Hours, 36 Minutes, 0 Seconds"
     * 
     * @param date1 first date Object
     * @param date2 second date Object
     * @return A String Denoting the "? Years, ? Months, ? Days, ? Hours, ? Minutes, ? Seconds" between date1 and date2
     * @throws ArithmeticException if numeric overflow occurs
     * @throws IllegalArgumentException Thrown if any of the arguments are null
     */
    public final String getChronoUnitBreakDownAsString(LocalDateTime date1, LocalDateTime date2) throws IllegalArgumentException,ArithmeticException{
        long[] breakDown = getChronoUnitBreakDownBetween(date1,date2);
        return breakDown[YEARS_IN_ARRAY] + " Years, " +
               breakDown[MONTHS_IN_ARRAY] + " Months, " +
               breakDown[DAYS_IN_ARRAY] + " Days, " +
               breakDown[HOURS_IN_ARRAY] + " Hours, " +
               breakDown[MINNUTES_IN_ARRAY] + " Minutes, " +
               breakDown[SECONDS_IN_ARRAY] + " Seconds";      
    }
    
    /**
     * Finds the next LocalDate occurrence where DayOfWeek and DateInMonth overlap<br>
     * Example:<br>
     * <b>date</b> = 11/6/2017<br>
     * <b>dow</b> = DayOfWeek.FRIDAY<br>
     * <b>dom</b> = 13 <br>
     * The Next Date with a Friday 13th would be: 04/13/2018
     * @param date start date to use for search
     * @param dow the DayOfWeek value desired
     * @param dom the Day Of Month value desired
     * @return LocalDate object who's value is the next occurrence of DayOfWeek with DayOfMonth
     * @throws IllegalArgumentException if date or dow is null OR 31 > dom > 1
     */
    public final LocalDate findNextDayOfWeekWithDateOfMonth(LocalDate date,DayOfWeek dow, int dom) throws IllegalArgumentException{
        if(date == null) throw new IllegalArgumentException("Date May Not Be Null");
        if(dow == null) throw new IllegalArgumentException("Day Of Week May Not Be Null");
        if(dom > 31 || dom < 1) throw new IllegalArgumentException("Day Of Month Must Be Between 1-31 inclusive");
        do{
            date = date.with(TemporalAdjusters.next(dow));
        }while(date.getDayOfMonth() != dom);
        return date;
    }
    
     /**
     * Finds the next LocalDate occurrence where DayOfWeek and DateInMonth overlap using LocalDate.now()
     * as argument for findNextDayOfWeekWithDateOfMonth(LocalDate,DayOfWeek,int)
     * 
     * @param dow the DayOfWeek value desired
     * @param dom the Day Of Month value desired
     * @return LocalDate object who's value is the next occurrence of DayOfWeek with DayOfMonth
     * @throws IllegalArgumentException if dow is null OR 31 > dom > 1
     */
    public final LocalDate findNextDayOfWeekWithDateOfMonth(DayOfWeek dow, int dom) throws IllegalArgumentException{
       return findNextDayOfWeekWithDateOfMonth(LocalDate.now(),dow,dom);
    }
    
    public final String formatLocalDateTimeToString(LocalDateTime date, String pattern) throws IllegalArgumentException{
        if(date == null || pattern == null || pattern.isEmpty()) throw new IllegalArgumentException("Arguments May Not Be Null Or Empty String");
        return DateTimeFormatter.ofPattern(pattern).format(date);
    }
    
    public final String formatLocalDateTimeToString(LocalDateTime date)throws IllegalArgumentException{
        return formatLocalDateTimeToString(date,defaultPattern);
    }
    
    public final LocalDateTime getLocalDateTimeFromString(String dateString) throws IllegalArgumentException{
        return getLocalDateTimeFromString(dateString,defaultPattern);
    }
    
    public final LocalDateTime getLocalDateTimeFromString(String dateString, String pattern)throws IllegalArgumentException{
        if(dateString == null || dateString.isEmpty() || pattern == null || pattern.isEmpty()) throw new IllegalArgumentException("Arguments May Not Be Null Or Empty");
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }    
    
    public final void setDefaultFormatPattern(String pattern) throws IllegalArgumentException{
        if(pattern == null || pattern.isEmpty()) throw new IllegalArgumentException("Pattern May Not Be Null Or Empty");
        defaultPattern = pattern;
    }
    
    public final String getDefaultFormatPattern(){
        return defaultPattern;
    }
}
