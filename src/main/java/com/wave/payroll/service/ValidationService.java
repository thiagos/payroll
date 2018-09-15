package com.wave.payroll.service;

import com.wave.payroll.exception.ValidationException;
import com.wave.payroll.model.dao.reportFile.ReportFileDAO;
import com.wave.payroll.model.dto.ReportFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private ReportFileDAO reportFileDAO;

    @Autowired
    public ValidationService(ReportFileDAO reportFileDAO) {
        this.reportFileDAO = reportFileDAO;
    }

    /**
     * Search for the current reportId in DB. IF already exists, stop processing
     * @param reportId
     * @return
     */
    public void isValidReportId(Long reportId) {
        System.out.println("all reports from DB:");
        System.out.println(reportFileDAO.findAll());
        ReportFile found = reportFileDAO.findByReportId(reportId);
        if (found != null) {
            throw new ValidationException("ReportId already exists:" + reportId);
        }
    }
}
