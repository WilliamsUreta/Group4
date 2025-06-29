package com.ams.Grupo4.controller;

import com.ams.Grupo4.model.Prod_Categoria;
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
 
import com.ams.Grupo4.Service.Prod_CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prod-categoria")
public class Prod_CategoriaController {

    @Autowired
    private Prod_CategoriaService prod_CategoriasService;

    //Obtener todas las relaciones producto-categoría
    @GetMapping
    public ResponseEntity<List<Prod_Categoria>> obtenerTodos() {
        List<Prod_Categoria> prodCategorias = prod_CategoriasService.obtenerTodos();
        if (prodCategorias.isEmpty()) { 
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prodCategorias); 
    }

    //Obtener una relación producto-categoría por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Prod_Categoria> obtenerPorId(@PathVariable Long id) {
        Prod_Categoria prodCategoria = prod_CategoriasService.obtenerPorId(id);
        if (prodCategoria == null) { 
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prodCategoria); 
    }

    //Crear una nueva relación producto-categoría
    @PostMapping
    public ResponseEntity<Prod_Categoria> guardar(@RequestBody Prod_Categoria prodCategoria) {
        Prod_Categoria nuevaProdCategoria = prod_CategoriasService.guardar(prodCategoria); 
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaProdCategoria);
    }

    //Actualizar una relación producto-categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<Prod_Categoria> actualizar(@RequestBody Prod_Categoria prodCategoria) {
        Prod_Categoria prodCat = prod_CategoriasService.guardar(prodCategoria);
        if (prodCat == null){
            return ResponseEntity.notFound().build(); 
        } else {
            return ResponseEntity.ok(prodCat);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Prod_Categoria> actualizarParcial(@PathVariable Long id, @RequestBody Prod_Categoria prodCategoria) {
        Prod_Categoria prodCatE = prod_CategoriasService.actualizar(id, prodCategoria);
        if (prodCatE == null) {
            return ResponseEntity.ok(prodCatE);
        }
        return ResponseEntity.notFound().build();
    }

    //Eliminar una relación producto-categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if(prod_CategoriasService.obtenerPorId(id) == null){
            return ResponseEntity.notFound().build();
        }
        prod_CategoriasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
