package com.wave.payroll.model.dao.timeReport;

import com.wave.payroll.model.dto.TimeReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeReportDAO {

    private TimeReportRepository repository;

    public TimeReportDAO(TimeReportRepository repository) {
        this.repository = repository;
    }

    public List<TimeReport> findAll() {
        List<TimeReport> entries = repository.findAll();
        return entries;
    }

    public void save(TimeReport timeReport) {
        repository.save(timeReport);
    }

    public void saveBatch(List<TimeReport> timeReportList) {
        repository.saveAll(timeReportList);
    }
}
