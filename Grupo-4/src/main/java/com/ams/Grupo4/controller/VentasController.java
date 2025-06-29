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

import com.ams.Grupo4.Service.VentasService;
import com.ams.Grupo4.model.Ventas;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    //Obtener todas las ventas
    @GetMapping
    public ResponseEntity<List<Ventas>> obtenerTodos() {
        List<Ventas> ventas = ventasService.obtenerTodos(); 
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(ventas); 
    }

    //Obtener una venta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Ventas> obtenerPorId(@PathVariable Long id) {
        Ventas venta = ventasService.obtenerPorId(id);
        if (venta == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(venta);
    }

    //Crear una nueva venta
    @PostMapping
    public ResponseEntity<Ventas> guardar(@RequestBody Ventas venta) {
        Ventas nuevaVenta = ventasService.guardar(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    //Actualizar una venta existente
    @PutMapping("/{id}")
    public ResponseEntity<Ventas> actualizar(@RequestBody Ventas venta) {
        Ventas ventaAct = ventasService.guardar(venta);
        if(ventaAct == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventaAct);
    }

    //Patch de Ventas
    @PatchMapping("/{id}")
    public ResponseEntity<Ventas> patchVentas(@PathVariable Long id, @RequestBody Ventas venta) {
        Ventas ventaAct = ventasService.actualizar(id, venta);
        if (ventaAct != null) {
            return ResponseEntity.ok(ventaAct);
        }
        return ResponseEntity.notFound().build();
    }

    //Eliminar una venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if(ventasService.obtenerPorId(id) == null){
            return ResponseEntity.notFound().build();
        }
        ventasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
