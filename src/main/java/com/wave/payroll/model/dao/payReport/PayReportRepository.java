package com.wave.payroll.model.dao.payReport;

import com.wave.payroll.model.dto.PayReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayReportRepository extends CrudRepository<PayReport, Long> {
}
