package com.wave.payroll.service;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class PeriodService {

    public String getPeriod(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        Integer month = cal.get(Calendar.MONTH);
        Integer day = cal.get(Calendar.DAY_OF_MONTH);

        Integer initDay = 1;
        Integer endDay = 15;
        if (day > 15) {
            initDay = 16;
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(cal.DAY_OF_MONTH));
            endDay = cal.get(Calendar.DAY_OF_MONTH);
        }
        return new StringBuilder().append(initDay).append("/").append(month+1).append("/").append(year).
                append(" - ").append(endDay).append("/").append(month+1).append("/").append(year).toString();
    }
}
