package com.ams.Grupo4.controller;

import com.ams.Grupo4.Service.CategoriaService;
import com.ams.Grupo4.model.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaServicio;

    //Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = categoriaServicio.obtenerCategorias(); //Obtiene todas las categorias del servicio
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build(); //Si la lista está vacia, retorna 204(No Content)
        }
        return ResponseEntity.ok(categorias); //Si hay categorias, retorna 200(OK) con la lista
    }

    //Obtener una categoría por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id); //Busca la categoria por ID, si no existe retorna null
        if (categoria != null) {
            return ResponseEntity.ok(categoria); //Si existe, retorna 200(OK) con la categoria
        }
        return ResponseEntity.notFound().build(); //Si no existe, retorna 404(Not Found)
    }

    //Crear una nueva categoría
    @PostMapping
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaServicio.guardarCategoria(categoria); //Guarda la nueva categoria en la base de datos
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria); //Retorna 201(Created) con la categoría creada
    }

    //Actualizar una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@RequestBody Categoria categoria) {
        Categoria categoriaAct = categoriaServicio.guardarCategoria(categoria);
        if(categoriaAct == null){
            return ResponseEntity.ok(categoriaAct);
        }
        return ResponseEntity.notFound().build();
    }

    //Modificar una categoría
    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> modificarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria categoriaP = categoriaServicio.actualizar(id, categoria);
        if (categoriaP != null){
            return ResponseEntity.ok(categoriaP);
        }
        return ResponseEntity.notFound().build();
    }

    //Eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try{
            categoriaServicio.eliminarCategoria(id);
            return ResponseEntity.notFound().build(); 
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
