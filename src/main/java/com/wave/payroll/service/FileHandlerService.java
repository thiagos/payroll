package com.wave.payroll.service;

import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileHandlerService {

//    // based on https://stackoverflow.com/questions/50987018/how-to-read-data-from-csv-file-and-store-it-in-the-database-spring-boot
//    public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
//        CsvMapper mapper = new CsvMapper();
//        CsvSchema schema = mapper.schemaFor(clazz).withHeader();
//        ObjectReader reader = mapper.readerFor(clazz).with(schema);
//        return reader.<T>readValues(stream).readAll();
//    }
}
