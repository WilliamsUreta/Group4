package com.ams.Grupo4.repository;

import com.ams.Grupo4.model.Categoria;
import com.ams.Grupo4.model.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
        // Buscar productos por nombre (ignorando mayúsculas/minúsculas)
        @Query("SELECT nombre FROM Producto WHERE LOWER(nombre) = LOWER(:nombre)")
        List<Producto> findByNombreContainingIgnoreCase(@Param("nombre")String nombre);
    
        // Buscar productos por rango de precio
        List<Producto> findByPrecioBetween(Float precioMin, Float precioMax);
        
        // Buscar productos con stock bajo
        List<Producto> findByStockLessThan(Integer stockMinimo);

        // Buscar productos por categoría
        List<Producto> findByCategoriaId(Long categoriaId);

        @Query("SELECT categoria FROM Producto WHERE categoria = :categoria")
        List<Producto> findByCate(@Param("categoria") Categoria categoria);
        
        @Query("SELECT  p.nombre, p.precio, c.categoria, m.metodoPago FROM Producto p JOIN p.categoria c JOIN p.metodoPago m")
        List<Object[]> obtenerProdCompra();

  
}
