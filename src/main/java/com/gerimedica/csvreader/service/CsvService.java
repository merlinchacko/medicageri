package com.gerimedica.csvreader.service;

import com.gerimedica.csvreader.exception.CodeNotFoundException;
import com.gerimedica.csvreader.helper.CsvHelper;
import com.gerimedica.csvreader.model.CodeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CsvService {


    private final Resource filePath = new ClassPathResource("exercise.csv");
    private final CsvHelper csvHelper;


    public List<CodeDetails> getAllCodeDetails() {
        //TODO read from h2
        return csvHelper.processInputFile(filePath);
    }

    public CodeDetails getCodeDetailsByCode(String code) {
        return getAllCodeDetails()
                .stream()
                .filter(q -> q.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new CodeNotFoundException("Code not found: " + code));

    }

    public void uploadAllCodeDetails() {
        //TODO read data and upload to h2
    }

    public void deleteCodeDetails() throws Exception {
        csvHelper.clearCsv(filePath.getFilename());
    }
}
