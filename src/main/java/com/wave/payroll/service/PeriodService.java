package com.wave.payroll.service;

import org.springframework.stereotype.Service;

import java.util.Calendar;


@Service
public class PeriodService {

    /**
     * finds the exact 15 day period for a given String day/month/year
     * @param date
     * @return
     */
    public String getPeriod(String date) {
        String[] dateParts = date.split("/");
        Integer day = Integer.parseInt(dateParts[0]);
        Integer month = Integer.parseInt(dateParts[1]);
        Integer year = Integer.parseInt(dateParts[2]);



        Integer initDay = 1;
        Integer endDay = 15;
        if (day > 15) {
            // use calendar to find last day of the month
            Calendar cal = Calendar.getInstance();
            cal.set(year, month-1, day);
            initDay = 16;
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(cal.DAY_OF_MONTH));
            endDay = cal.get(Calendar.DAY_OF_MONTH);
        }
        return new StringBuilder().append(initDay).append("/").append(month).append("/").append(year).
                append(" - ").append(endDay).append("/").append(month).append("/").append(year).toString();
    }
}
