package com.wave.payroll.service;

import com.wave.payroll.model.dao.payReport.PayReportDAO;
import com.wave.payroll.model.dao.reportFile.ReportFileDAO;
import com.wave.payroll.model.dao.timeReport.TimeReportDAO;
import com.wave.payroll.model.dto.PayReport;
import com.wave.payroll.model.dto.ReportFile;
import com.wave.payroll.model.dto.TimeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayrollService {

    private final RateService rateService;
    private final PeriodService periodService;
    private final FileHandlerService fileHandlerService;

    private final TimeReportDAO timeReportDAO;
    private final PayReportDAO payReportDAO;
    private final ReportFileDAO reportFileDAO;

    // map storing all pay reports
    private Map<String, PayReport> payReportMap;

    // list containing all created/modified keys in current timeReport file processing, if any
    private List<PayReport> createdPayReports;
    private List<PayReport> modifiedPayReports;

    @Autowired
    public PayrollService(RateService rateService,
                          PeriodService periodService,
                          FileHandlerService fileHandlerService,
                          TimeReportDAO timeReportDAO,
                          PayReportDAO payReportDAO,
                          ReportFileDAO reportFileDAO) {
        this.rateService = rateService;
        this.periodService = periodService;
        this.fileHandlerService = fileHandlerService;
        this.payReportDAO = payReportDAO;
        this.timeReportDAO = timeReportDAO;
        this.reportFileDAO = reportFileDAO;
    }

    public void addTimeEntriesToPayReport(MultipartFile mpFile) throws IOException {

        // convert mpFile to list of TimeReport instances
        List<TimeReport> timeReportList = fileHandlerService.fileToCsv(mpFile);

        createdPayReports = new ArrayList<>();
        modifiedPayReports = new ArrayList<>();

        // retrieve current payReportMap from DB
        payReportMap = getPayReportFromDb();

        // add new time report into existing data
        for (TimeReport newEntry : timeReportList) {
            processTimeEntry(newEntry);
        }
        // save all new time reports to DB
        timeReportDAO.saveBatch(timeReportList);
        // save new/update modified pay reports
        insertOrUpdatePayReports();

        // insert report in DB, to mark as processed and check for duplicates
        reportFileDAO.save(new ReportFile(fileHandlerService.getCurrentReportId()));
    }

    private void processTimeEntry(TimeReport timeReport) {

        // quick workaround to avoid processing last entry, which contains report id
        // find a better solution later
        if (timeReport.getDate().equals("report id")) {
            return;
        }

        // calculate rate
        Double dollarAmount = rateService.calculateRate(timeReport.getJobGroup(), timeReport.getHours());

        // retrieve period in format "dd/mm/yyyy - dd/mm/yyyy" based on current date
        String payPeriod = periodService.getPeriod(timeReport.getDate());

        // any hours for a given employee, on a same period, needs to be aggregated
        // so, use the concatenation of these values as key on a map
        String payReportKey = getPayReportKey(timeReport.getEmployeeId(), payPeriod);


        // add initial value or increment existing value
        if (!payReportMap.containsKey(payReportKey)) {
            PayReport payReport = new PayReport(timeReport.getEmployeeId(), payPeriod, dollarAmount);
            payReportMap.put(payReportKey, payReport);
            // track new pay reports, to be inserted
            createdPayReports.add(payReport);
        } else {
            PayReport payReport = payReportMap.get(payReportKey);
            payReport.setAmountPaid(payReport.getAmountPaid() + dollarAmount);
            // track modified pay reports, only these shall be updated
            if (!modifiedPayReports.contains(payReport)) {
                modifiedPayReports.add(payReport);
            }
        }
    }

    private Map<String, PayReport> getPayReportFromDb() {
        List<PayReport> payReportList = payReportDAO.findAll();

        // convert list to map, for easier aggregation
        Map<String, PayReport> payReportMap = new HashMap<>();
        for (PayReport payReport : payReportList) {
            payReportMap.put(getPayReportKey(payReport), payReport);
        }

        return payReportMap;
    }

    private void insertOrUpdatePayReports() {

        payReportDAO.saveAll(createdPayReports);
        // TODO insert update method for entries in modifiedPayReports
    }

    // methods for pay report key. If changes required, change in these 2 methods
    private String getPayReportKey(PayReport payReport) {
        return payReport.getEmployeeId() + "_" + payReport.getPeriod();
    }
    private String getPayReportKey(Long employeeId, String period) {
        return employeeId + "_" + period;
    }

    public List<TimeReport> getTimeEntriesFromDb() {
        return timeReportDAO.findAll();
    }

    public List<PayReport> getPayEntriesFromDb() {
        List<PayReport> payReportList = payReportDAO.findAll();
        // sorting by period and employeeId
        Collections.sort(payReportList);
        return payReportList;
    }

    // getters ands setters
    public Map<String, PayReport> getPayReportMap() {
        return payReportMap;
    }

    public void setPayReportMap(Map<String, PayReport> payReportMap) {
        this.payReportMap = payReportMap;
    }
}
