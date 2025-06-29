package com.ams.Grupo4.Service;

import com.ams.Grupo4.model.Categoria;
import com.ams.Grupo4.model.Producto;
import com.ams.Grupo4.repository.CategoriaRepository;
import com.ams.Grupo4.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;
 
    //Obtiene todas las categorías
    public List<Categoria> obtenerCategorias() { 
        return categoriaRepository.findAll(); 
    }

    //Obtiene una categoría por su ID
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    //Guarda una nueva categoría
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    //Elimina una categoría por ID
    public void eliminarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        List<Producto> productos = productoRepository.findByCate(categoria);

        for(Producto producto : productos){
            productoService.eliminar(Long.valueOf(producto.getId()));
        }
        
        categoriaRepository.delete(categoria);
    }

    public Categoria actualizar(Long id, Categoria categoria){
        Categoria categoriaE = obtenerCategoriaPorId(id);
        if(categoriaE != null){
            if(categoria.getCategoria() != null){
                categoriaE.setCategoria(categoria.getCategoria());
            }
            return guardarCategoria(categoriaE);
        }
        return null;
    }

}
