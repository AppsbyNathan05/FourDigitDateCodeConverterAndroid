package com.apps_by_nathan05.fourdigitdatecodeconverter;

import org.junit.Test;

import java.util.Calendar;

public class DateToDateCodeTest {

    @Test
    public void dateToDateCodeTest() {

        final ConversionCalculations call = new ConversionCalculations();

        Calendar cal = Calendar.getInstance();
        cal.set(1969, 11, 29);
        int weekLimit = 53;
        int passCount = 0;
        int failCount = 0;
        String conversionResult = "";
        String incrementalResult = "";
        String yearCode = "";
        String weekCode = "";

        for (int year=1970; year<2020; year++) {

            // 53 week years 70 76 81 87 92 98 04 09 15
            if (year == 1970 || year == 1976 ||year == 1981 ||
                    year == 1987 || year == 1992 ||year == 1998 ||
                    year == 2004 || year == 2009 ||year == 2015) {
                weekLimit = 54;
            } else {
                weekLimit = 53;
            } // end if

            if (year % 100 > 9) {
                yearCode = "" + year % 100;
            } else {
                yearCode = "0" + year % 100;
            } // end if

            for (int week=1; week<weekLimit; week++) {

                if (week > 9) {
                    weekCode = "" + week;
                } else {
                    weekCode = "0" + week;
                } // end if

                for (int day=1; day<8; day++) {

                    conversionResult = (cal.get(Calendar.MONTH) + 1) + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.YEAR) + " " + " Converts to " +
                            " " + call.getDateCode(cal, false);

                    incrementalResult = (cal.get(Calendar.MONTH) + 1) + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.YEAR) + " " + " Converts to " +
                            " " + yearCode + weekCode;

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

                    conversionResult = (cal.get(Calendar.MONTH) + 1) + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.YEAR) + " " + " Converts to " +
                            " " + call.getDateCode(cal, true);

                    incrementalResult = (cal.get(Calendar.MONTH) + 1) + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.YEAR) + " " + " Converts to " +
                            " " + weekCode + yearCode;

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

                    cal.add(Calendar.DATE, 1);

                } // end for

            } //end for

        } // end for

        System.out.println("PASSED " + passCount);
        System.out.println("FAILED " + failCount);

    }

}
