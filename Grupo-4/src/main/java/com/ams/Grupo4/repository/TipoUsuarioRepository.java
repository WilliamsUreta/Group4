package com.ams.Grupo4.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ams.Grupo4.model.TipoUsuario;
import com.ams.Grupo4.model.Usuario;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long>{
    // Buscar tipo de usuario por nombre
    @Query("SELECT tu FROM TipoUsuario tu WHERE tu.nombre = :nombre")
    List<TipoUsuario> findByNombre(@Param("nombre")String nombre);
    
    // Verificar si existe un tipo de usuario por nombre
    boolean existsByNombre(String nombre);

    @Query("SELECT  tu.nombre, u.nombre, u.correo FROM Usuario u JOIN u.tipoUsuario tu")
    List<Object[]> InfiUsuario();

}
