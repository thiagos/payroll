package com.wave.payroll.model.dao.reportFile;

import com.wave.payroll.model.dto.ReportFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportFileRepository extends JpaRepository<ReportFile, Long> {

    ReportFile findByReportId(Long reportId);
}
