package com.ams.Grupo4.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ams.Grupo4.model.ProductosVentas;

@Repository
public interface ProductosVentasRepository extends JpaRepository<ProductosVentas, Long>{
        // Buscar productos por venta
        List<ProductosVentas> findByVentasId(Long ventaId);
    
        // Buscar ventas por producto
        List<ProductosVentas> findByProductoId(Long productoId);

        
}
