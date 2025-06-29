package com.ams.Grupo4.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ams.Grupo4.model.EstadoDeVenta;
import com.ams.Grupo4.model.ProductosVentas;
import com.ams.Grupo4.model.TipoUsuario;
import com.ams.Grupo4.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
        
        // Buscar usuario por correo
        @Query("SELECT correo FROM Usuario WHERE correo = :correo")
        List<Usuario> findByCorreo(@Param("correo")String correo);
        
        // Buscar usuarios por nombre (búsqueda parcial)
        @Query("SELECT nombre FROM Usuario WHERE nombre = :nombre")
        List<Usuario> findByNombreContaining(@Param("nombre")String nombre);

        @Query("SELECT tipoUsuario FROM Usuario WHERE tipoUsuario = :tipoUsuario")
        List<Usuario> findByTipoUsuario(@Param("tipoUsuario")TipoUsuario tipoUsuario);

        // Verificar si existe un correo
        boolean existsByCorreo(String correo);

        // Buscar usuarios por tipo de usuario
        List<Usuario> findByTipoUsuarioId(Long tipoUsuarioId);


        @Query("SELECT  u.nombre, p.producto.nombre, ev.estado, t.nombre FROM Usuario u JOIN EstadoDeVenta ev JOIN ProductosVentas p JOIN TipoUsuario t")
        List<Object[]> obtenerTodo();

    
}
