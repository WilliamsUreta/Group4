package com.ams.Grupo4.controller.controllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ams.Grupo4.Assembler.Prod_CategoriaAssembler;
import com.ams.Grupo4.Service.Prod_CategoriaService;
import com.ams.Grupo4.model.Prod_Categoria;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v2/Prod-Categoria")
public class Prod_CategoriaControllerV2 {

    @Autowired
        private Prod_CategoriaService prod_CategoriaService;

    @Autowired
        private Prod_CategoriaAssembler assembler;

    @GetMapping(produces=MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Prod_Categoria>>> GetAllProd_Categoria(){
        List<EntityModel<Prod_Categoria>> prod_Categoria = prod_CategoriaService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if(prod_Categoria.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            prod_Categoria,
            linkTo(methodOn(Prod_CategoriaControllerV2.class).GetAllProd_Categoria()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Prod_Categoria>> getProd_CategoriaByid(@PathVariable Long id){
        Prod_Categoria prod_Categoria = prod_CategoriaService.obtenerPorId(id);          
        if (prod_Categoria == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(prod_Categoria));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Prod_Categoria>> CreateProd_Categoria(@RequestBody Prod_Categoria prod_Categoria){
        Prod_Categoria newProd_Categoria =prod_CategoriaService.guardar(prod_Categoria);
        return ResponseEntity
                .created(linkTo(methodOn(Prod_CategoriaControllerV2.class).getProd_CategoriaByid(Long.valueOf(newProd_Categoria.getId()))).toUri())
                .body(assembler.toModel(newProd_Categoria));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Prod_Categoria>> UpdateProd_Categoria(@PathVariable Long id, @RequestBody Prod_Categoria prod_Categoria){
        Prod_Categoria UpdatePC = prod_CategoriaService.actualizar(id, prod_Categoria);
        if(UpdatePC == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(UpdatePC));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Prod_Categoria>> PatchProd_Categoria(@PathVariable Long id, @RequestBody Prod_Categoria prod_Categoria){
        Prod_Categoria UpdateProd_Categoria = prod_CategoriaService.actualizar(id, prod_Categoria);
        if (UpdateProd_Categoria == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(UpdateProd_Categoria));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> DeleteProd_Categoria (@PathVariable Long id){
        Prod_Categoria prod_Categoria = prod_CategoriaService.obtenerPorId(id);
        if(prod_Categoria == null){
            return ResponseEntity.notFound().build();
        }
        prod_CategoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
