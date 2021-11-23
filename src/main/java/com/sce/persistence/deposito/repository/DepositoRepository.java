package com.sce.persistence.deposito.repository;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.deposito.entity.Deposito;

public interface DepositoRepository extends CrudRepository<Deposito, Long> {
}
