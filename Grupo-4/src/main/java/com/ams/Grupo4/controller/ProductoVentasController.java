package com.ams.Grupo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.ams.Grupo4.Service.ProductoVentasService;
import com.ams.Grupo4.model.ProductosVentas;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos-ventas")
public class ProductoVentasController {

    @Autowired
    private ProductoVentasService productoVentasService;

    //Obtener todas las relaciones producto-venta
    @GetMapping
    public ResponseEntity<List<ProductosVentas>> obtenerTodos() {
        List<ProductosVentas> productosVentas = productoVentasService.obtenerTodos(); 
        if (productosVentas.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(productosVentas); 
    }

    //Obtener una relación producto-venta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductosVentas> obtenerPorId(@PathVariable Long id) {
        ProductosVentas productoVenta = productoVentasService.obtenerPorId(id);
        if (productoVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoVenta);
    }

    //Crear una nueva relación producto-venta
    @PostMapping
    public ResponseEntity<ProductosVentas> guardar(@RequestBody ProductosVentas productoVenta) {
        ProductosVentas nuevaProductoVenta = productoVentasService.guardar(productoVenta); 
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaProductoVenta);
    }

    //Actualizar una relación producto-venta existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductosVentas> actualizar(@RequestBody ProductosVentas productoVenta) {
        ProductosVentas productoVentaE = productoVentasService.guardar(productoVenta);
        if (productoVentaE != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoVentaE); 
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductosVentas> patchProdVent(@PathVariable Long id, @RequestBody ProductosVentas productoVenta) {
        ProductosVentas productosVentasP = productoVentasService.patchProductV(id, productoVenta);
        if(productosVentasP == null){
            return ResponseEntity.ok(productosVentasP);
        }
        return ResponseEntity.ok(productosVentasP);
    }

    //Eliminar una relación producto-venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if(productoVentasService.obtenerPorId(id) == null){
            return ResponseEntity.notFound().build();
        }
        productoVentasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

   /*//Obtener todas las relaciones de un producto específico
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ProductosVentas>> obtenerPorProducto(@PathVariable Long productoId) {
        List<ProductosVentas> productosVentas = productoVentasService.obtenerPorProducto(productoId); //Busca todas las relaciones del producto
        if (productosVentas.isEmpty()) {
            return ResponseEntity.noContent().build(); //Si no hay relaciones, retorna 204(No Content)
        }
        return ResponseEntity.ok(productosVentas); //Si hay relaciones, retorna 200(OK) con la lista
    }

    //Obtener todas las relaciones de una venta específica
    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<ProductosVentas>> obtenerPorVenta(@PathVariable Long ventaId) {
        List<ProductosVentas> productosVentas = productoVentasService.obtenerPorVenta(ventaId); //Busca todas las relaciones de la venta
        if (productosVentas.isEmpty()) {
            return ResponseEntity.noContent().build(); //Si no hay relaciones, retorna 204(No Content)
        }
        return ResponseEntity.ok(productosVentas); //Si hay relaciones, retorna 200(OK) con la lista
    }*/
}
