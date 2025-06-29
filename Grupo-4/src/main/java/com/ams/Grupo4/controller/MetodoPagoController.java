package com.ams.Grupo4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.ams.Grupo4.Service.MetodoPagoService;
import com.ams.Grupo4.model.MetodoPago;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metodoPago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    //Obtener todos los métodos de pago
    @GetMapping
    public ResponseEntity<List<MetodoPago>> obtenerTodos() {
        List<MetodoPago> metodosPago = metodoPagoService.ObtenerTodos();
        if (metodosPago.isEmpty()) {
            return ResponseEntity.noContent().build();  // Retorna 204 si no hay datos
        }
        return ResponseEntity.ok(metodosPago);  // Retorna 200 con la lista de métodos de pago
    }

    //Obtener un método de pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtenerPorId(@PathVariable Long id) {
        MetodoPago metodoPago = metodoPagoService.ObtenerPorId(id);
        if (metodoPago == null) {
            return ResponseEntity.notFound().build();  // Retorna 404 si no existe
        }
        return ResponseEntity.ok(metodoPago);  // Retorna 200 con el método de pago
    }

    //Crear un nuevo método de pago
    @PostMapping
    public ResponseEntity<MetodoPago> guardar(@RequestBody MetodoPago metodoPago) {
        MetodoPago nuevoMetodoPago = metodoPagoService.Guardar(metodoPago);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMetodoPago);  // Retorna 201 con el nuevo método de pago
    }

    //Actualizar método de pago
    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizar(@PathVariable Long id, @RequestBody MetodoPago metodoPago) {
        MetodoPago actualizado = metodoPagoService.Actualizar(id, metodoPago);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);  // Retorna 200 con el método de pago actualizado
        }
        return ResponseEntity.notFound().build();  // Retorna 404 si no existe
    }

    //Eliminar método de pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try{
            metodoPagoService.Eliminar(id);
            return ResponseEntity.noContent().build();  // Retorna 204 si se eliminó correctamente
        }catch(Exception e){
        return ResponseEntity.notFound().build();  // Retorna 404 si no existe
        }
    }
}
