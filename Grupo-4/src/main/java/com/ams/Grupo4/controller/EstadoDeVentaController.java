package com.ams.Grupo4.controller;

import com.ams.Grupo4.Service.EstadoDeVentaServicio;
import com.ams.Grupo4.model.EstadoDeVenta;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estadoDeVenta")
public class EstadoDeVentaController {

    @Autowired
    private EstadoDeVentaServicio estadoDeVentaServicio;

    //Obtener todos los estados de venta
    @GetMapping
    public ResponseEntity<List<EstadoDeVenta>> obtenerTodos() {
        List<EstadoDeVenta> estadosDeVenta = estadoDeVentaServicio.ObtenerTodos();
        if (estadosDeVenta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadosDeVenta);
    }

    //Obtener un estado de venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstadoDeVenta> obtenerPorId(@PathVariable Long id) {
        EstadoDeVenta estado = estadoDeVentaServicio.ObtenerPorId(id);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }
 
    //Crear un nuevo estado de venta
    @PostMapping
    public ResponseEntity<EstadoDeVenta> Guardar(@RequestBody EstadoDeVenta estado) {
        EstadoDeVenta nuevoEstado = estadoDeVentaServicio.Guardar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstado);
    }
    
    //Actualizar estado de venta
    @PutMapping("/{id}")
    public ResponseEntity<EstadoDeVenta> actualizar(@RequestBody EstadoDeVenta estadoDeVenta) {
        EstadoDeVenta estadoAct = estadoDeVentaServicio.Guardar(estadoDeVenta);
        if(estadoAct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estadoAct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstadoDeVenta> modificar(@PathVariable Long id, @RequestBody EstadoDeVenta estadoDeVenta) {
        EstadoDeVenta estadoExistente = estadoDeVentaServicio.ObtenerPorId(id);
        if (estadoExistente == null) {
            return ResponseEntity.ok(estadoExistente);
        }
        return ResponseEntity.notFound().build();
        
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Eliminar(@PathVariable Long id) {
        if(estadoDeVentaServicio.ObtenerPorId(id) == null){
            return ResponseEntity.notFound().build();
        }
        estadoDeVentaServicio.Eliminar(id);
        return ResponseEntity.noContent().build();
    } 
}  
