package com.wave.payroll.model.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReportDAO {

    @Value("database.host")
    String host;
    @Value("database.port")
    String port;


}
