package com.gerimedica.csvreader.endpoint;

import com.gerimedica.csvreader.model.CodeDetails;
import com.gerimedica.csvreader.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/csv-reader")
public class CsvEndpoint {
    @Autowired
    CsvService csvService;

    @GetMapping
    public List<CodeDetails> getAllCodeDetails() {
        return csvService.getAllCodeDetails();
    }

    @GetMapping(value = "/{code}")
    public CodeDetails getCodeDetailsByCode(@PathVariable String code) {
        return csvService.getCodeDetailsByCode(code);
    }

    @PostMapping
    public void uploadAllCodeDetails() {
        csvService.uploadAllCodeDetails();
    }

    @DeleteMapping
    public void deleteCodeDetails() throws Exception {
        csvService.deleteCodeDetails();
    }
}




