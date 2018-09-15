package com.wave.payroll.service;

import com.wave.payroll.model.dto.PayPeriod;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class PayPeriodServiceTest {

    private PeriodService periodService;

    @Before
    public void setup() {
        periodService = new PeriodService();
    }


//    @Test
//    public void testGetPeriod() {
//
//        Map<Date, PayPeriod> testCases = new HashMap();
//        testCases.put(TestUtils.buildDate(2018, 0, 01), new PayPeriod(2018, 1));
//        testCases.put(TestUtils.buildDate(2015, 0, 16), new PayPeriod(2015, 2));
//        testCases.put(TestUtils.buildDate(2020, 4, 10), new PayPeriod(2020, 9));
//        testCases.put(TestUtils.buildDate(2019, 9, 15), new PayPeriod(2019, 19));
//        testCases.put(TestUtils.buildDate(2019, 9, 16), new PayPeriod(2019, 20));
//        testCases.put(TestUtils.buildDate(2018, 11, 31), new PayPeriod(2018, 24));
//
//        for (Map.Entry<Date, PayPeriod> entry : testCases.entrySet()) {
//            Pa = periodService.getPeriod(entry.getKey());
//            PayPeriod expectedResult = entry.getValue();
//            assertEquals(expectedResult.getYear(), testPayPeriod.getYear());
//            assertEquals(expectedResult.getPeriodId(), testPayPeriod.getPeriodId());
//            assertEquals(expectedResult, testPayPeriod);
//        }
//    }

    @Test
    public void testGetPeriod() {
        Map<Date, String> testCases = new HashMap();
        testCases.put(TestUtils.buildDate(2018, 0, 1), "1/1/2018 - 15/1/2018");
        testCases.put(TestUtils.buildDate(2019, 0, 20), "16/1/2019 - 31/1/2019");
        testCases.put(TestUtils.buildDate(2019, 10, 7), "1/11/2019 - 15/11/2019");
        testCases.put(TestUtils.buildDate(2019, 1, 20), "16/2/2019 - 28/2/2019");
        testCases.put(TestUtils.buildDate(2020, 1, 20), "16/2/2020 - 29/2/2020");
        testCases.put(TestUtils.buildDate(2019, 11, 31), "16/12/2019 - 31/12/2019");
        testCases.put(TestUtils.buildDate(2021, 0, 1), "1/1/2021 - 15/1/2021");
        testCases.put(TestUtils.buildDate(2015, 9, 15), "1/10/2015 - 15/10/2015");
        testCases.put(TestUtils.buildDate(2016, 10, 16), "16/11/2016 - 30/11/2016");

        for (Map.Entry<Date, String> entry : testCases.entrySet()) {
            String expectedResult = entry.getValue();
            String calculatedResult = periodService.getPeriod(entry.getKey());
            assertEquals(expectedResult, calculatedResult);
        }


    }
}
