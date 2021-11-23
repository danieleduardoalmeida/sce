package com.sce.persistence.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sce.persistence.usuario.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByLoginAndSenha(String login, String senha);

    Optional<Usuario> findByLoginOrEmail(String login, String email);
    
    Optional<Usuario> findByLogin(String login);
    
    Optional<Usuario> findByIdAndSenha(long id, String senha);

    @Query(value = "SELECT u.id FROM Usuario u WHERE (u.email = :email OR u.login = :login) AND u.id != :id")
    Optional<Usuario> findByEmailOrLoginEqualAndIdNot(@Param("email") String email,
                                                         @Param("login") String login,
                                                         @Param("id") Long id);

    @Query(value = "SELECT u FROM Usuario u WHERE "
            + "u.tipo NOT LIKE %:type% ")
    List<Usuario> findAllExceptType(@Param("type") String type);
    
    @Modifying
    @Query("update Usuario u set u.senha = :senha where u.id = :id")
    void updateSenha(@Param("senha") String senha, @Param("id") Long id);
}
