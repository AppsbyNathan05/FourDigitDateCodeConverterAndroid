package com.apps_by_nathan05.fourdigitdatecodeconverter;

import java.util.Calendar;

public class ConversionCalculations {

    protected Calendar getFirstDay(int week, int year) {
        Calendar cal = Calendar.getInstance();

        // Get century right, up to 10 years past current year is current century, 10+ is last century
        if ((cal.get(Calendar.YEAR) % 100) + 10 >= year) {
            year = (cal.get(Calendar.YEAR) / 100) * 100 + year;
            cal.set(year, 0, 1);
        } else {
            year = (cal.get(Calendar.YEAR) / 100 - 1) * 100 + year;
            cal.set(year, 0, 1);
        } // end if

        // Set first day of date code year
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            cal.set(year, 0, 1);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            cal.set(year - 1, 11, 31);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            cal.set(year - 1, 11, 30);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            cal.set(year - 1, 11, 29);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            cal.set(year, 0, 4);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.set(year, 0, 3);
        } else {
            cal.set(year, 0, 2);
        } // end if

        // Add weeks to date
        cal.add(Calendar.DATE, (week - 1) * 7);

        return cal;

    } // end getFirstDay

    protected Calendar getStartDay (Calendar cal) {

        // Set first day of date code year
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            cal.set(cal.get(Calendar.YEAR), 0, 1);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            cal.set(cal.get(Calendar.YEAR) - 1, 11, 31);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            cal.set(cal.get(Calendar.YEAR) - 1, 11, 30);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            cal.set(cal.get(Calendar.YEAR) - 1, 11, 29);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            cal.set(cal.get(Calendar.YEAR), 0, 4);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.set(cal.get(Calendar.YEAR), 0, 3);
        } else {
            cal.set(cal.get(Calendar.YEAR), 0, 2);
        } // end if

        return cal;

    } // end getStartDay

    protected String getDateCode (Calendar cal, Boolean wwyyDC) {

        String dateCode;
        String weekCode;
        String yearCode;
        int year = cal.get(Calendar.YEAR);
        int week = 0;

        Calendar startCal = Calendar.getInstance();
        Calendar lastYearCal = Calendar.getInstance();
        Calendar nextYearCal = Calendar.getInstance();
        startCal.set(year, 0, 1);
        lastYearCal.set(year - 1, 0, 1);
        nextYearCal.set(year + 1, 0, 1);

        startCal.setTime(getStartDay(startCal).getTime());
        lastYearCal.setTime(getStartDay(lastYearCal).getTime());
        nextYearCal.setTime(getStartDay(nextYearCal).getTime());

        if (cal.before(startCal) && !(cal.get(Calendar.YEAR) == startCal.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) == startCal.get(Calendar.MONTH)
                && cal.get(Calendar.DAY_OF_MONTH) == startCal.get(Calendar.DAY_OF_MONTH))) {
            year = year - 1;
            startCal.set(lastYearCal.get(Calendar.YEAR), lastYearCal.get(Calendar.MONTH), lastYearCal.get(Calendar.DAY_OF_MONTH));
        } else if (cal.after(nextYearCal) || (cal.get(Calendar.YEAR) == nextYearCal.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) == nextYearCal.get(Calendar.MONTH)
                && cal.get(Calendar.DAY_OF_MONTH) == nextYearCal.get(Calendar.DAY_OF_MONTH))) {
            year = year + 1;
            startCal.set(nextYearCal.get(Calendar.YEAR), nextYearCal.get(Calendar.MONTH), nextYearCal.get(Calendar.DAY_OF_MONTH));
        } // end if

        while ((cal.after(startCal) || (cal.get(Calendar.YEAR) == startCal.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) == startCal.get(Calendar.MONTH)
                && cal.get(Calendar.DAY_OF_MONTH) == startCal.get(Calendar.DAY_OF_MONTH))) && week < 54) {
            week = week + 1;
            startCal.add(Calendar.DATE, 7);
        } //end while

        if (year % 100 > 9) {
            yearCode = "" + year % 100;
        } else {
            yearCode = "0" + year % 100;
        } // end if

        if (week > 9) {
            weekCode = "" + week;
        } else {
            weekCode = "0" + week;
        } // end if

        if (wwyyDC) {
            dateCode = weekCode + yearCode;
        } else {
            dateCode = yearCode + weekCode;
        } // end if

        return dateCode;

    } // end getDateCode

}
