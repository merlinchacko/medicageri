package com.gerimedica.csvreader.service;

import com.gerimedica.csvreader.exception.CodeNotFoundException;
import com.gerimedica.csvreader.helper.CsvHelper;
import com.gerimedica.csvreader.model.CodeDetails;
import com.gerimedica.csvreader.repository.CsvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CsvService {


    private final Resource filePath = new ClassPathResource("exercise.csv");
    private final CsvHelper csvHelper;
    @Autowired
    CsvRepository csvRepository;


    public List<CodeDetails> getAllCodeDetails() {
        List<CodeDetails> codeDetails = new ArrayList<CodeDetails>();
        csvRepository.findAll().forEach(codeDetail -> codeDetails.add(codeDetail));
        return codeDetails;
    }

    public CodeDetails getCodeDetailsByCode(String code) {
        return getAllCodeDetails()
                .stream()
                .filter(q -> q.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new CodeNotFoundException("Code not present"));

    }

    public void uploadAllCodeDetails() {
        try {
            List<CodeDetails> codeDetailsList = csvHelper.processInputFile(filePath);
            csvRepository.saveAll(codeDetailsList);
        } catch (Exception e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void deleteCodeDetails() throws Exception {
        csvRepository.deleteAll();
    }
}
