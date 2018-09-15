package com.wave.payroll.controller;

import com.wave.payroll.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PayrollRestController {

    private final PayrollService payrollService;

    @Autowired
    public PayrollRestController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @RequestMapping(value = "/getReport", produces = "text/csv")
    public void generateReport(){

    }

    @RequestMapping(value = "/addTimeReport", produces = "text/csv")
    public void addTimeReport(){}

    @RequestMapping(value = "/uploadTimeReport", method = RequestMethod.POST)
    public void test(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException {
        payrollService.addTimeEntriesToPayReport(multiPartFile);
        System.out.println("print entries:");
        System.out.println(payrollService.getPayReportMap());
        System.out.println("time entries from DB");
        System.out.println(payrollService.getTimeEntriesFromDb());
        System.out.println("pay entries from DB");
        System.out.println(payrollService.getPayEntriesFromDb());
    }
}
