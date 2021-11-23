package com.sce.persistence.imovel.repository;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.imovel.entity.Imovel;

public interface ImovelRepository extends CrudRepository<Imovel, Long>{
}
