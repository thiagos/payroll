package com.wave.payroll.service;

import com.wave.payroll.model.dto.PayReportKey;
import com.wave.payroll.model.dto.PayPeriod;
import com.wave.payroll.model.dto.TimeReportEntry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AggregationService {

    private RateService rateService;
    private PeriodService periodService;
    private Map<PayReportKey, Double> payReport;

    public AggregationService(Map<PayReportKey, Double> payReport) {
        this.payReport = payReport;
        this.rateService.initializeRates();
    }

    public void addTimeEntriesToPayReport(List<TimeReportEntry> newEntries) {

        // add new time report into existing data
        for (TimeReportEntry newEntry : newEntries) {
            processTimeEntry(newEntry);
        }
    }

    private void processTimeEntry(TimeReportEntry entry) {
        // calculate rate
        Double dollarAmount = rateService.calculateRate(entry.getJobGroup(), entry.getHours());
        String payPeriod = periodService.getPeriod(entry.getDate());
        //PayReportKey  payReportKey = new PayReportKey(entry.getEmployeeId(), payPeriod);

        // add initial value or increment existing value
//        if (!payReport.containsKey(payReportKey)) {
//            payReport.put(payReportKey, dollarAmount);
//        } else {
//            payReport.put(payReportKey, dollarAmount + payReport.get(payReportKey));
//        }
    }
}
