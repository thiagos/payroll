package com.wave.payroll.model.dao.timeReport;

import com.wave.payroll.model.dto.TimeReportEntry;

import java.util.List;

public interface ITimeReportEntryDAO {

    public List<TimeReportEntry> findAll();

}
