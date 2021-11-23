package com.sce.persistence.especie.repository;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.especie.entity.Especie;

public interface EspecieRepository extends CrudRepository<Especie, Long> {
}
