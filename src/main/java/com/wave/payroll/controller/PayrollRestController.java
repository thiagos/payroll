package com.wave.payroll.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class PayrollRestController {

    @RequestMapping(value = "/getReport", produces = "text/csv")
    public void generateReport(){}

}
