package com.wave.payroll.model.dto;


import java.util.Objects;

public class PayPeriod implements Comparable<PayPeriod> {

    private Integer year;
    // periodId goes from 1 (Jan 1 - Jan 15) to 24 (Dec 16 - Dec 31)
    private Integer periodId;

    public PayPeriod(Integer year, Integer periodId) {
        this.year = year;
        this.periodId = periodId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        PayPeriod compared = (PayPeriod) o;
        return Objects.equals(year, compared.getYear()) &&
                Objects.equals(periodId, compared.getPeriodId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, periodId);
    }

    @Override
    public int compareTo(PayPeriod p) {
        if (this.year != p.getYear()) {
            return this.year.compareTo(p.getYear());
        } else {
            return this.periodId.compareTo(p.getPeriodId());
        }
    }
}
