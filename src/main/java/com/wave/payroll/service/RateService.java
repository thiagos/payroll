package com.wave.payroll.service;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RateService {

    Map<String, Double> rates;

    public void initializeRates() {
        // retrieve from DB/config, hardcode for now
        rates.put("A", 20.0);
        rates.put("B", 30.0);
    }

    public Double calculateRate(String rate, Double hours) {
        return rates.get(rate) * hours;
    }
}
