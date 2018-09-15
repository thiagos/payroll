package com.wave.payroll.model.dao.timeReport;

import com.wave.payroll.model.dto.TimeReportEntry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TimeReportEntryDAO implements ITimeReportEntryDAO {

    @Autowired
    private TimeReportEntryRepository repository;

    @Override
    public List<TimeReportEntry> findAll() {
        List<TimeReportEntry> entries = (List<TimeReportEntry>) repository.findAll();
        return entries;
    }
}
