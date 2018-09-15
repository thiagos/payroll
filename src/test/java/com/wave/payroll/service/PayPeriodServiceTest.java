package com.wave.payroll.service;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PayPeriodServiceTest {

    private PeriodService periodService;

    @Before
    public void setup() {
        periodService = new PeriodService();
    }

    @Test
    public void testGetPeriod() {
        Map<String, String> testCases = new HashMap();
        testCases.put("1/1/2018", "1/1/2018 - 15/1/2018");
        testCases.put("20/1/2019", "16/1/2019 - 31/1/2019");
        testCases.put("7/11/2019", "1/11/2019 - 15/11/2019");
        testCases.put("20/2/2019", "16/2/2019 - 28/2/2019");
        testCases.put("20/02/2020", "16/2/2020 - 29/2/2020");
        testCases.put("31/12/2019", "16/12/2019 - 31/12/2019");
        testCases.put("03/1/2021", "1/1/2021 - 15/1/2021");
        testCases.put("15/10/2015", "1/10/2015 - 15/10/2015");
        testCases.put("16/11/2016", "16/11/2016 - 30/11/2016");

        for (Map.Entry<String, String> entry : testCases.entrySet()) {
            String expectedResult = entry.getValue();
            String calculatedResult = periodService.getPeriod(entry.getKey());
            assertEquals(expectedResult, calculatedResult);
        }


    }
}
