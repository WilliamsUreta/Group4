package com.ams.Grupo4.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ams.Grupo4.model.Prod_Categoria;

@Repository
public interface Prod_CategoriaRepository extends JpaRepository<Prod_Categoria, Long>{
        // Buscar relaciones por producto
        List<Prod_Categoria> findByProductoId(Long productoId);
    
        // Buscar relaciones por categoría
        List<Prod_Categoria> findByCategoriaId(Long categoriaId);
        
        // Verificar si existe una relación específica
        boolean existsByProductoIdAndCategoriaId(Long productoId, Long categoriaId);
}
