package com.wave.payroll.service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wave.payroll.model.dto.TimeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.bean.CsvToBean;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.List;


@Service
public class FileHandlerService {

    private ValidationService validationService;
    private Long currentReportId;

    @Autowired
    public FileHandlerService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public List<TimeReport> fileToCsv(MultipartFile mpFile) throws IOException {
        File convFile = new File(mpFile.getOriginalFilename());

        String lastLine = tail(convFile);
        currentReportId = new Long(lastLine.split(",")[1]);

        validationService.isValidReportId(currentReportId);

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(mpFile.getBytes());
        fos.close();

        List<TimeReport> timeReportEntries;
        try (Reader reader = new FileReader(convFile)) {
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(TimeReport.class);
            CsvToBean<TimeReport> csvToBean = new CsvToBeanBuilder<TimeReport>(reader)
                    .withType(TimeReport.class)
                    .withSkipLines(1)
                    .build();
            timeReportEntries = csvToBean.parse();
        }
        return timeReportEntries;
    }

    public String tail(File file) {
        RandomAccessFile fileHandler = null;
        try {
            fileHandler = new RandomAccessFile( file, "r" );
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for(long filePointer = fileLength; filePointer != -1; filePointer--){
                fileHandler.seek( filePointer );
                int readByte = fileHandler.readByte();

                if( readByte == 0xA ) {
                    if( filePointer == fileLength ) {
                        continue;
                    }
                    break;

                } else if( readByte == 0xD ) {
                    if( filePointer == fileLength - 1 ) {
                        continue;
                    }
                    break;
                }

                sb.append( ( char ) readByte );
            }

            String lastLine = sb.reverse().toString();
            return lastLine;
        } catch( java.io.FileNotFoundException e ) {
            e.printStackTrace();
            return null;
        } catch( java.io.IOException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileHandler != null )
                try {
                    fileHandler.close();
                } catch (IOException e) {
                    /* ignore */
                }
        }
    }

    public Long getCurrentReportId() {
        return currentReportId;
    }

    public void setCurrentReportId(Long currentReportId) {
        this.currentReportId = currentReportId;
    }
}
