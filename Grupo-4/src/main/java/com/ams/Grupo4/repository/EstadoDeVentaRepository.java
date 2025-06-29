package com.ams.Grupo4.repository;

import com.ams.Grupo4.model.EstadoDeVenta;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EstadoDeVentaRepository extends JpaRepository<EstadoDeVenta, Long>{
        
        /*@Query("SELECT e FROM EstadoDeVenta e WHERE e.estado = :estado")
        List<EstadoDeVenta> findByEstado(@Param("estado") String estado);*/

        

        List<EstadoDeVenta> findByEstado(String estado);
        // Verificar si existe un estado
        boolean existsByEstado(String estado);
}
