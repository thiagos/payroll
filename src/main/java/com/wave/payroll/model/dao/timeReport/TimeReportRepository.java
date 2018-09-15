package com.wave.payroll.model.dao.timeReport;

import com.wave.payroll.model.dto.TimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeReportRepository extends JpaRepository<TimeReport, Long> {
}
