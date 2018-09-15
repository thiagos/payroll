package com.wave.payroll.model.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pay_report")
public class PayReportEntry implements Comparable<PayReportEntry>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long employeeId;
    private String period;
    private Long amountPaid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, period, amountPaid);
    }

    @Override
    public int compareTo(PayReportEntry payReportEntry) {
        if (this.getEmployeeId() != payReportEntry.getEmployeeId()) {
            return this.getEmployeeId().compareTo(payReportEntry.getEmployeeId());
        } else {
            return this.getPeriod().compareTo(payReportEntry.getPeriod());
        }
    }
}
