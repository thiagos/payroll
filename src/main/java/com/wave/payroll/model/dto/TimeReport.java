package com.wave.payroll.model.dto;

import com.opencsv.bean.CsvBindByPosition;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;

@Entity
@Table(name = "time_report_entries")
public class TimeReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CsvBindByPosition(position = 0)
    private String date;

    @CsvBindByPosition(position = 1)
    private Double hours;

    @CsvBindByPosition(position = 2)
    private Long employeeId;

    @CsvBindByPosition(position = 3)
    private String jobGroup;

    public TimeReport() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public String toString() {
        return "id=" + id +
                ", date=" + date +
                ", hours=" + hours +
                ", employeeId=" + employeeId +
                ", jobGroup=" + jobGroup + "\n";
    }
}
