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

import com.ams.Grupo4.Service.UsuarioService;
import com.ams.Grupo4.model.Usuario;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos(); 
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    //Obtener un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(usuario); 
    }

    //Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario); 
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    //Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario) {
        Usuario usuarioE = usuarioService.guardar(usuario);
        if (usuarioE == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioE);
    }

    //Actualizar por patch
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> patchUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioP = usuarioService.patchUsuario(id, usuario);
        if(usuarioP == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioP);
    }

    //Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (usuarioService.findById(id) == null){
            return ResponseEntity.notFound().build();
        } 
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //Buscar usuarios por tipo de usuario
    @GetMapping("/tipo/{tipoUsuarioId}")
    public ResponseEntity<List<Usuario>> buscarPorTipoUsuario(@PathVariable Long tipoUsuarioId) {
        List<Usuario> usuarios = usuarioService.buscarPorTipoUsuario(tipoUsuarioId); //Busca usuarios por tipo
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); //Si no hay usuarios, retorna 204(No Content)
        }
        return ResponseEntity.ok(usuarios); //Si hay usuarios, retorna 200(OK) con la lista
    }

    //Buscar usuarios por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Usuario>> buscarPorNombre(@PathVariable String nombre) {
        List<Usuario> usuarios = usuarioService.buscarPorNombre(nombre); //Busca usuarios por nombre
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); //Si no hay usuarios, retorna 204(No Content)
        }
        return ResponseEntity.ok(usuarios); //Si hay usuarios, retorna 200(OK) con la lista
    }
}
