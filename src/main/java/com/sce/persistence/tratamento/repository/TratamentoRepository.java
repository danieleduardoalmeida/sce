package com.sce.persistence.tratamento.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.tratamento.entity.Tratamento;

public interface TratamentoRepository extends CrudRepository<Tratamento, Long> {

    Optional<Tratamento> findById(Long id);
    Optional<Tratamento> findByDepositoIdAndVistoriaId(long depositoId, long vistoriaId);
    Optional<Tratamento> findByVistoriaId(long vistoriaId);
}
