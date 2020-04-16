package com.automation;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class DataManipulationUtils {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final DateFormat stf = new SimpleDateFormat("HH:mm");
    private static final DateFormat fullstf = new SimpleDateFormat("HH:mm:ss");

    /**
     * -- modifyTime --
     *
     * @param dtIn
     * @param hourChangeType
     * @param hours
     * @param minsChangeType
     * @param mins
     * @return
     */
    public static String modifyTime(Date dtIn, String hourChangeType, int hours, String minsChangeType, int mins) {


        Calendar cal = Calendar.getInstance();
        cal.setTime(dtIn);

        switch (hourChangeType.toLowerCase()) {

            case "add":
                cal.add(Calendar.HOUR, hours);
                break;
            case "minus":
                cal.add(Calendar.HOUR, hours - (hours * 2));
                break;
            case "none":
                break;
            default:
                System.out.println("unknown Hour Modifier :" + hourChangeType + " Expected \'add\' or \"minus\" only");


        }


        switch (minsChangeType.toLowerCase()) {

            case "add":
                cal.add(Calendar.MINUTE, mins);
                break;
            case "minus":
                cal.add(Calendar.MINUTE, mins - (mins * 2));
                break;
            case "none":
                break;
            default:
                System.out.println("unknown minute Modifier :" + minsChangeType + " Expected \'add\', \"minus\" or \"none\" only");


        }


        return stf.format(cal.getTime());

    }


    /**
     * -- addMins --
     *
     * @param Time
     * @param mins
     * @return
     */
    public static Calendar addMins(Date Time, int mins) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(Time);

        cal.add(Calendar.MINUTE, mins);


        return cal;


    }


    /**
     * -- minusMins --
     *
     * @param Time
     * @param mins
     * @return
     */
    public static Calendar minusMins(Date Time, int mins) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(Time);

        mins = mins - (mins * 2);

        cal.add(Calendar.MINUTE, mins);

        return cal;


    }

    /**
     * -- minusHours --
     *
     * @param dateTime
     * @param hours
     * @return
     */
    public static Calendar minusHours(Date dateTime, int hours) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(dateTime);

        hours = hours - (hours * 2);

        cal.add(Calendar.HOUR, hours); //minus number would decrement the days

        return cal;


    }

    /**
     * -- addHours --
     *
     * @param dateTime
     * @param hours
     * @return
     */
    public static Calendar addHours(Date dateTime, int hours) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(dateTime);

        cal.add(Calendar.HOUR, hours); //minus number would decrement the days

        return cal;


    }


    /**
     * -- addDays --
     *
     * @param date
     * @param days
     * @return
     */
    public static Calendar addDays(Date date, int days) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.add(Calendar.DATE, days); //minus number would decrement the days

        return cal;

    }


    /**
     * -- addMonths --
     *
     * @param date
     * @param iMonths
     * @return
     */
    public static String addMonths(Date date, int iMonths) {


        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.add(Calendar.MONTH, iMonths); //minus number would decrement the days

        return cal.getTime().toString();


    }

    /**
     * -- addYears--
     *
     * @param date
     * @param iYears
     * @return
     */
    public static String addYears(Date date, int iYears) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        cal.add(Calendar.YEAR, iYears); //minus number would decrement the days

        return cal.getTime().toString();


    }

    /**
     *
     * -- getRandomString --
     *
     * @param iLength - Expected length of String to return
     * @return - String of required value
     */
    public static String getRandomString(int iLength) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = iLength;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }
}
