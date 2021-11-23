package com.sce.persistence.analise.especie.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.analise.especie.entity.AnaliseEspecie;
import com.sce.persistence.analise.especie.entity.AnaliseEspecieId;

public interface AnaliseEspecieRepository extends CrudRepository<AnaliseEspecie, AnaliseEspecieId> {

    List<AnaliseEspecie> findAllByIdAnaliseId(long analiseId);
    List<AnaliseEspecie> findAllByIdEspecieId(long especieId);
}
