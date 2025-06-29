package com.ams.Grupo4.controller.controllerV2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import com.ams.Grupo4.Assembler.CategoriaModelAssembler;
import com.ams.Grupo4.Service.CategoriaService;
import com.ams.Grupo4.model.Categoria;




@RestController
@RequestMapping("/api/v2/categorias")
public class CategoriaControllerv2 {
     
    @Autowired
        private CategoriaService categoriaService;

    @Autowired
        private CategoriaModelAssembler assembler;


    //obtiene todas las categorias?
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Categoria>>> getAllCategorias(){
        List<EntityModel<Categoria>> categorias = categoriaService.obtenerCategorias().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (categorias.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
            categorias,
            linkTo(methodOn(CategoriaControllerv2.class).getAllCategorias()).withSelfRel()
        ));
            }   

    //obtiene categorias por ID?
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Categoria>> getCategoriaByid(@PathVariable Long id){
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(categoria));
    }

    // Crear una nueva categoria
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Categoria>> createCategoria(@RequestBody Categoria categoria){
        Categoria newCategoria = categoriaService.guardarCategoria(categoria);
        return ResponseEntity
                .created(linkTo(methodOn(CategoriaControllerv2.class).getCategoriaByid(Long.valueOf(newCategoria.getId()))).toUri())
                .body(assembler.toModel(newCategoria));
    }

    // Actualizar una categoria
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Categoria>> updateCategoria(@PathVariable Long id,@RequestBody Categoria categoria){
        Categoria cat = categoriaService.guardarCategoria(categoria);
        if(cat == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(cat)); 
    }

    // Actualizar parcialmente una categoria
    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Categoria>> PatchCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
        Categoria categoriaAct = categoriaService.actualizar(id, categoria);
        if(categoriaAct == null){
            return ResponseEntity.ok(assembler.toModel(categoriaAct));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar una categoria
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteCategoria (@PathVariable Long id){
        if(categoriaService.obtenerCategoriaPorId(id) == null){
            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    
    
}












