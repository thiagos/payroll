package com.wave.payroll.model.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "time_report_entries")
public class TimeReportEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private Double hours;
    private Long employeeId;
    private String jobGroup;

    public TimeReportEntry(Long id, Date date, Double hours, Long employeeId, String jobGroup) {
        this.id = id;
        this.date = date;
        this.hours = hours;
        this.employeeId = employeeId;
        this.jobGroup = jobGroup;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
}
