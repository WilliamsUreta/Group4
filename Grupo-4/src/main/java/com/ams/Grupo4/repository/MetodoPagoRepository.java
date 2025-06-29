package com.ams.Grupo4.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ams.Grupo4.model.MetodoPago;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long>{
        // Buscar método de pago por nombre
        @Query("SELECT metodoPago FROM MetodoPago WHERE metodoPago = :metodoPago")
        List<MetodoPago> findByMetodoPago(@Param("metodoPago")MetodoPago metodoPago);
}
