package com.ams.Grupo4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.Grupo4.model.ProductosVentas;
import com.ams.Grupo4.repository.ProductosVentasRepository;

import java.util.List;

@Service
public class ProductoVentasService {
    
    @Autowired
    private ProductosVentasRepository productosVentasRepository;

    //Obtener todas las relaciones producto-venta
    public List<ProductosVentas> obtenerTodos() {
        return productosVentasRepository.findAll();
    }

    //Obtener una relación producto-venta por su ID
    public ProductosVentas obtenerPorId(Long id) {
        return productosVentasRepository.findById(id).orElse(null);
    }

    //Guardar una nueva relación producto-venta
    public ProductosVentas guardar(ProductosVentas productoVenta) {
        return productosVentasRepository.save(productoVenta);
    }

    //Eliminar una relación producto-venta por su ID
    public void eliminar(Long id) {
        ProductosVentas productoVenta = productosVentasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Relación producto-venta no encontrada"));
        
        productosVentasRepository.delete(productoVenta);
    }


    public ProductosVentas patchProductV(Long id, ProductosVentas productosVentas){
        ProductosVentas productVentE = obtenerPorId(id);
        if(productVentE != null){
            if (productosVentas.getProducto() != null){
                productVentE.setProducto(productosVentas.getProducto());
            }
            if (productosVentas.getVentas() != null){
                productVentE.setVentas(productosVentas.getVentas());
            }
            return guardar(productVentE);
        }
        return null;
    }

}
