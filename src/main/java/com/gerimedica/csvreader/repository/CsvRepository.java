package com.gerimedica.csvreader.repository;

import com.gerimedica.csvreader.model.CodeDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvRepository  extends CrudRepository<CodeDetails, Integer> {
}
