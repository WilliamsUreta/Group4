package com.ams.Grupo4.Service;

import com.ams.Grupo4.model.Prod_Categoria;
import com.ams.Grupo4.repository.Prod_CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Prod_CategoriaService {

    @Autowired
    private Prod_CategoriaRepository prod_CategoriaRepository;

    //Obtener todas las relaciones producto-categoría de la base de datos
    public List<Prod_Categoria> obtenerTodos() {
        return prod_CategoriaRepository.findAll();
    }

    //Obtener una relación producto-categoría por su ID
    public Prod_Categoria obtenerPorId(Long id) {
        return prod_CategoriaRepository.findById(id).orElse(null);
    }

    //Guardar una nueva relación en la base de datos
    public Prod_Categoria guardar(Prod_Categoria prodCategoria) {
        return prod_CategoriaRepository.save(prodCategoria);
    }

    //Eliminar una relación por su ID
    public void eliminar(Long id) {
       Prod_Categoria prodCat = prod_CategoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Relación producto-categoría no encontrada"));
        
        prod_CategoriaRepository.delete(prodCat);
    }

    //Actualizar una relación existente
    public Prod_Categoria actualizar(Long id, Prod_Categoria prodCategoria) {
        Prod_Categoria prodCatE = obtenerPorId(id);
        if (prodCatE != null) {
            if(prodCatE.getProducto() != null) {
                prodCatE.setProducto(prodCategoria.getProducto());
            }
            return guardar(prodCatE);
        }
        return null;
    }

    //Obtener todas las categorías de un producto específico
    public List<Prod_Categoria> obtenerCategoriasPorProducto(Long productoId) {
        return prod_CategoriaRepository.findByProductoId(productoId);
    }

    //Obtener todos los productos de una categoría específica
    public List<Prod_Categoria> obtenerProductosPorCategoria(Long categoriaId) {
        return prod_CategoriaRepository.findByCategoriaId(categoriaId);
    }

    //Verificar si existe una relación entre un producto y una categoría
    public boolean existeRelacion(Long productoId, Long categoriaId) {
        return prod_CategoriaRepository.existsByProductoIdAndCategoriaId(productoId, categoriaId);
    }

    //Eliminar todas las categorías de un producto
    public void eliminarCategoriasDeProducto(Long productoId) {
        List<Prod_Categoria> relaciones = prod_CategoriaRepository.findByProductoId(productoId);
        prod_CategoriaRepository.deleteAll(relaciones);
    }

    //Eliminar todos los productos de una categoría
    public void eliminarProductosDeCategoria(Long categoriaId) {
        List<Prod_Categoria> relaciones = prod_CategoriaRepository.findByCategoriaId(categoriaId);
        prod_CategoriaRepository.deleteAll(relaciones);
    }
}
