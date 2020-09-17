package com.gerimedica.csvreader.helper;

import com.gerimedica.csvreader.exception.FileNotFoundException;
import com.gerimedica.csvreader.model.CodeDetails;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvHelper {

    private static final String COMMA = ",";

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

    private final Function<String, CodeDetails> mapToItem = (line) -> {
        String[] p = line.split(COMMA);
        return CodeDetails.builder()
                .source(p[0].replaceAll("\"", "")).codeListCode(p[1].replaceAll("\"", ""))
                .code(p[2].replaceAll("\"", "")).displayValue(p[3].replaceAll("\"", "")).longDescription(p[4]).fromDate(p[5])
                .toDate(p[6]).sortingPriority(p[7]).build();

    };

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
