package com.wave.payroll.service;

import java.util.Date;
import java.util.GregorianCalendar;

public class TestUtils {

    public static Date buildDate(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }
}
