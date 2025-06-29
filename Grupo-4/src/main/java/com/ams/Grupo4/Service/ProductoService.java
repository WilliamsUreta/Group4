package com.ams.Grupo4.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.Grupo4.model.Producto;
import com.ams.Grupo4.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    //Obtener todos los productos 
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
 
    //Obtener un producto por su ID
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    //Guardar un nuevo producto 
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    //Eliminar un producto por su ID
    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        productoRepository.delete(producto);
    }

    //Actualizar un producto existente
    public Producto actualizar(Long id, Producto producto) {
        Producto productoE = obtenerPorId(id);
        if (productoE != null) {
            if (producto.getNombre() != null) {
                productoE.setNombre(producto.getNombre());
            }
            if (producto.getPrecio() != null) {
                productoE.setPrecio(producto.getPrecio());
            }
            if (producto.getStock() != null) {
                productoE.setStock(producto.getStock());
            }
            if (producto.getCategoria() != null) {
                productoE.setCategoria(producto.getCategoria());
            }
            return productoRepository.save(productoE);
        }
        return null;
    }

    //Buscar productos por nombre 
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    //Buscar productos por categoría
    public List<Producto> buscarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

}
