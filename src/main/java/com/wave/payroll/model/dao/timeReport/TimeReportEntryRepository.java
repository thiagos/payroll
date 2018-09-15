package com.wave.payroll.model.dao.timeReport;

import com.wave.payroll.model.dto.TimeReportEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeReportEntryRepository extends CrudRepository<TimeReportEntry, Long> {
}
