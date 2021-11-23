package com.sce.persistence.exemplar.repository;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.exemplar.entity.Exemplar;

public interface ExemplarRepository extends CrudRepository<Exemplar, Long> {
}
