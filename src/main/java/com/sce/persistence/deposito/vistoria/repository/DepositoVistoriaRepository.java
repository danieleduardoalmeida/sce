package com.sce.persistence.deposito.vistoria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.deposito.vistoria.entity.DepositoVistoria;
import com.sce.persistence.deposito.vistoria.entity.DepositoVistoriaId;

public interface DepositoVistoriaRepository extends CrudRepository<DepositoVistoria, DepositoVistoriaId>{

    List<DepositoVistoria> findAllByIdDepositoId(long depositoId);
    List<DepositoVistoria> findAllByIdVistoriaId(long vistoriaId);
}
