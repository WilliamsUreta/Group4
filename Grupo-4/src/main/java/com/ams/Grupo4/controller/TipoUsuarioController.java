package com.ams.Grupo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.ams.Grupo4.Service.TipoUsuarioService;
import com.ams.Grupo4.model.TipoUsuario;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-usuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    //Obtener todos los tipos de usuario
    @GetMapping
    public ResponseEntity<List<TipoUsuario>> obtenerTodos() {
        List<TipoUsuario> tiposUsuario = tipoUsuarioService.findAll(); 
        if (tiposUsuario.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(tiposUsuario); 
    }

    //Obtener un tipo de usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> obtenerPorId(@PathVariable Long id) {
        TipoUsuario tipoUsuario = tipoUsuarioService.findById(id); 
        if (tipoUsuario == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(tipoUsuario); 
    }

    //Crear un nuevo tipo de usuario
    @PostMapping
    public ResponseEntity<TipoUsuario> guardar(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario nuevoTipoUsuario = tipoUsuarioService.guardar(tipoUsuario); 
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipoUsuario); 
    }

    //Actualizar un tipo de usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> actualizar(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario) {
        try{
            TipoUsuario tUser = tipoUsuarioService.actualizar(id, tipoUsuario);
            return ResponseEntity.ok(tUser);
        }catch( Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Eliminar un tipo de usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try{
            tipoUsuarioService.deleteById(id);
            return ResponseEntity.noContent().build(); 
        } catch (Exception e){
            return ResponseEntity.notFound().build(); 
        }
    }

}
