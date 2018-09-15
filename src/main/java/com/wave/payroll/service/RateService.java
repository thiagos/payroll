package com.wave.payroll.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RateService {

    Map<String, Double> rates;

    public RateService() {
        initializeRates();
    }

    public void initializeRates() {
        // retrieve from DB/config, hardcode for now
        rates = new HashMap<>();
        rates.put("A", 20.0);
        rates.put("B", 30.0);
    }

    public Double calculateRate(String rate, Double hours) {
        return rates.get(rate) * hours;
    }
}
