package com.wave.payroll.model.dto;

import java.util.Objects;

public class PayReportKey implements Comparable<PayReportKey> {

    private Long employeeId;
    private PayPeriod payPeriod;

    public PayReportKey(Long employeeId, PayPeriod payPeriod) {
        this.employeeId = employeeId;
        this.payPeriod = payPeriod;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public PayPeriod getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(PayPeriod payPeriod) {
        this.payPeriod = payPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        PayReportKey compared = (PayReportKey) o;
        return Objects.equals(employeeId, compared.getEmployeeId()) &&
                Objects.equals(payPeriod, compared.getPayPeriod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, payPeriod);
    }

    @Override
    public int compareTo(PayReportKey payReportKey) {
        if (this.getEmployeeId() != payReportKey.getEmployeeId()) {
            return this.getEmployeeId().compareTo(payReportKey.getEmployeeId());
        } else {
            return this.getPayPeriod().compareTo(payReportKey.getPayPeriod());
        }
    }
}
