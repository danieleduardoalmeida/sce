package com.sce.persistence.analise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.analise.entity.Analise;

public interface AnaliseRepository extends CrudRepository<Analise, Long> {

    @Query(value = "SELECT a FROM Analise a WHERE "
            + "a.dataConclusao IS NULL")
    List<Analise> findAllOpenAnalises();
}
