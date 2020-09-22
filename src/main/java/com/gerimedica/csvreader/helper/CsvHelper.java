package com.gerimedica.csvreader.helper;

import com.gerimedica.csvreader.exception.FileNotFoundException;
import com.gerimedica.csvreader.model.CodeDetails;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CsvHelper {

    private static final String COMMA = "\",";
    private static final AtomicInteger uniqueId = new AtomicInteger(100);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

    private final Function<String, CodeDetails> mapToItem = (line) -> {

        String[] p = line.split(COMMA);
        CodeDetails codeDetails = new CodeDetails();

        codeDetails.setId(nextValue());
        codeDetails.setSource(p[0].replaceAll("\"", ""));
        codeDetails.setCodeListCode(p[1].replaceAll("\"", ""));
        codeDetails.setCode(p[2].replaceAll("\"", ""));
        codeDetails.setDisplayValue(p[3].replaceAll("\"", ""));
        codeDetails.setLongDescription(p[4].replaceAll("\"", ""));
        String fromDate = p[5].replaceAll("\"", "");
        codeDetails.setFromDate(fromDate.length() > 0 ? LocalDate.parse(fromDate, formatter) : null);
        String toDate = p[6].replaceAll("\"", "");
        codeDetails.setToDate(toDate.length() > 0 ? LocalDate.parse(toDate, formatter) : null);
        String sortingPriority = p[7].replaceAll("\"", "");
        codeDetails.setSortingPriority(sortingPriority.length() > 0 ? Integer.parseInt(sortingPriority) : null);

        return codeDetails;
    };

    private int nextValue() {
        return uniqueId.getAndIncrement();
    }

    public List<CodeDetails> processInputFile(Resource inputFilePath) {
        List<CodeDetails> inputList = new ArrayList<CodeDetails>();
        try {
            File inputF = inputFilePath.getFile();
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (Exception e) {
            throw new FileNotFoundException("File not found.");
        }
        return inputList;
    }

    public void clearCsv(String filename) throws Exception {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename, false);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new Exception();
            }
        }
    }
}
