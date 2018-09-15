package com.wave.payroll.model.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pay_report")
public class PayReport implements Comparable<PayReport>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long employeeId;
    private String period;
    private Double amountPaid;

    public PayReport() {}

    public PayReport(Long employeeId, String period, Double amountPaid) {
        this.employeeId = employeeId;
        this.period = period;
        this.amountPaid = amountPaid;
    }

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

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, period, amountPaid);
    }

    @Override
    public int compareTo(PayReport payReport) {
        if (this.getEmployeeId() != payReport.getEmployeeId()) {
            return this.getEmployeeId().compareTo(payReport.getEmployeeId());
        } else {
            try {
                // compare periods
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                // just the beginning of the period is enough to compare
                Date thisPeriod = sdf.parse(this.getPeriod().split(" ")[0]);
                Date thatPeriod = sdf.parse(payReport.getPeriod().split(" ")[0]);
                return thisPeriod.compareTo(thatPeriod);
            } catch (ParseException e) {
                // TODO raise exception to be fixed
                System.out.println("exception parsing pay report date:" + e);
                return 1;
            }
        }
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", employeeId=" + employeeId +
                ", period=" + period +
                ", amountPaid=" + amountPaid + "\n";
    }
}
