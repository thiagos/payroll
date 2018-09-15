package com.wave.payroll.model.dto;

import javax.persistence.*;

@Entity
@Table(name = "report_file")
public class ReportFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long reportId;

    public ReportFile() {}

    public ReportFile(Long reportId) {
        this.reportId = reportId;
    }

    @Override
    public String toString() {
        return "reportId=" + reportId + "\n";
    }
}
