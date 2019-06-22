package com.apps_by_nathan05.fourdigitdatecodeconverter;

import org.junit.Test;

import java.util.Calendar;

public class DateCodeToDateTest {

    @Test
    public void dateCodeToDateTest() {

        final ConversionCalculations call = new ConversionCalculations();

        Calendar incrementalCal = Calendar.getInstance();
        incrementalCal.set(1969, 11, 29);
        Calendar conversionCal = Calendar.getInstance();
        int weekLimit = 53;
        int passCount = 0;
        int failCount = 0;
        String conversionResult = "";
        String incrementalResult = "";

        for (int year=1970; year<2020; year++) {

            // 53 week years 70 76 81 87 92 98 04 09 15
            if (year == 1970 || year == 1976 ||year == 1981 ||
                    year == 1987 || year == 1992 ||year == 1998 ||
                    year == 2004 || year == 2009 ||year == 2015) {
                weekLimit = 54;
            } else {
                weekLimit = 53;
            } // end if

            for (int week=1; week<weekLimit; week++) {

                incrementalResult = "Date is between " +
                        (incrementalCal.get(Calendar.MONTH) + 1) + "/" +
                        incrementalCal.get(Calendar.DAY_OF_MONTH) + "/" +
                        incrementalCal.get(Calendar.YEAR) + " and";
                // Calculate last day of week
                incrementalCal.add(Calendar.DATE, 6);
                // Add last day of week to results
                incrementalResult = incrementalResult + " " +
                        (incrementalCal.get(Calendar.MONTH) + 1) + "/" +
                        incrementalCal.get(Calendar.DAY_OF_MONTH) + "/" +
                        incrementalCal.get(Calendar.YEAR);

                // Calculate the first day of the week for the D/C WWYY
                conversionCal.setTime(call.getFirstDay(week, year % 100).getTime());

                conversionResult = "Date is between " +
                        (conversionCal.get(Calendar.MONTH) + 1) + "/" +
                        conversionCal.get(Calendar.DAY_OF_MONTH) + "/" +
                        conversionCal.get(Calendar.YEAR) + " and";
                // Calculate last day of week
                conversionCal.add(Calendar.DATE, 6);
                // Add last day of week to results
                conversionResult = conversionResult + " " +
                        (conversionCal.get(Calendar.MONTH) + 1) + "/" +
                        conversionCal.get(Calendar.DAY_OF_MONTH) + "/" +
                        conversionCal.get(Calendar.YEAR);

                if (conversionResult.equals(incrementalResult)) {
                    System.out.println("PASS");
                    passCount++;
                    System.out.println(passCount);
                } else {
                    System.out.println("FAIL");
                    failCount++;
                    System.out.println(conversionResult);
                    System.out.println(incrementalResult);
                }

                incrementalCal.add(Calendar.DATE, 1);

            } // end for

        } //End for

        System.out.println("PASSED " + passCount);
        System.out.println("FAILED " + failCount);

    }

}
