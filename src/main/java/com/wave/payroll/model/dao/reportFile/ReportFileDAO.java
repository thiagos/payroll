package com.wave.payroll.model.dao.reportFile;


import com.wave.payroll.model.dto.ReportFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportFileDAO {

    private ReportFileRepository repository;

    @Autowired
    public ReportFileDAO(ReportFileRepository repository) {
        this.repository = repository;
    }

    public ReportFile findByReportId(Long reportId) {
        return repository.findByReportId(reportId);
    }

    public List<ReportFile> findAll() {
        return repository.findAll();
    }

    public void save(ReportFile reportFile) {
        repository.save(reportFile);
    }
}
