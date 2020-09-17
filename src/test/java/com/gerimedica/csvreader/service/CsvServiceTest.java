package com.gerimedica.csvreader.service;

import com.gerimedica.csvreader.helper.CsvHelper;
import com.gerimedica.csvreader.model.CodeDetails;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CsvServiceTest {

    private CsvService csvService = new CsvService(new CsvHelper());

    @Test
    public void getAllCodeDetails() throws IOException {
        List<CodeDetails> details = csvService.getAllCodeDetails();

        assertEquals(18, details.size());
    }

    @Test
    public void getCodeDetailsByCode() throws IOException {
        CodeDetails details = csvService.getCodeDetailsByCode("271636001");

        assertEquals("Polsslag regelmatig", details.getDisplayValue());
    }
}
