package com.sce.persistence.vistoria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sce.persistence.vistoria.entity.Vistoria;

public interface VistoriaRepository extends CrudRepository<Vistoria, Long> {

    List<Vistoria> findAllByUsuarioId(long usuarioId);

    List<Vistoria> findAllByImovelId(long imovelId);
}
