package com.ams.Grupo4.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ams.Grupo4.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
        
        // Buscar por Categoria
        @Query("SELECT categoria FROM Categoria WHERE categoria = :categoria")
        List<Categoria> findByCategoria(@Param("categotia")Categoria categoria);
        
}
