package com.gerimedica.csvreader.repository;

import com.gerimedica.csvreader.model.CodeDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CsvRepository  extends CrudRepository<CodeDetails, Integer> {

    @Query("SELECT c FROM CodeDetails c where c.code = :code")
    public Optional<CodeDetails> findByCode(@Param("code") String code);
}
