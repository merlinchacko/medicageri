package com.gerimedica.csvreader.model;


import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CodeDetails {

    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private String fromDate;
    private String toDate;
    private String sortingPriority;

}
