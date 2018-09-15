package com.wave.payroll.model.dao.payReport;

import com.wave.payroll.model.dto.PayReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PayReportDAO {

    private PayReportRepository repository;

    public PayReportDAO(PayReportRepository repository) {
        this.repository = repository;
    }

    public List<PayReport> findAll() {
        return (List<PayReport>) repository.findAll();
    }

    public void save(PayReport payReport) { repository.save(payReport); }

    public void saveAll(List<PayReport> payReports) {
        repository.saveAll(payReports);
    }
}
