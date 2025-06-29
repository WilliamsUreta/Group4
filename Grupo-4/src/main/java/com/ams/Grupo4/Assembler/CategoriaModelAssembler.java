package com.ams.Grupo4.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.controller.controllerV2.CategoriaControllerv2;
import com.ams.Grupo4.model.Categoria;

@Component
public class CategoriaModelAssembler implements RepresentationModelAssembler<Categoria, EntityModel <Categoria>>{
    
    @SuppressWarnings("null")
    @Override
    public EntityModel <Categoria> toModel (Categoria categoria){
        return EntityModel.of(categoria,
        linkTo(methodOn(CategoriaControllerv2.class).getCategoriaByid(Long.valueOf(categoria.getId()))).withSelfRel(),
        linkTo(methodOn(CategoriaControllerv2.class).getAllCategorias()).withRel("Categorias"),
        linkTo(methodOn(CategoriaControllerv2.class).updateCategoria(Long.valueOf(categoria.getId()),categoria)).withRel("Actualizar"),
        linkTo(methodOn(CategoriaControllerv2.class).deleteCategoria(Long.valueOf(categoria.getId()))).withRel("Eliminar"),
        linkTo(methodOn(CategoriaControllerv2.class).PatchCategoria(Long.valueOf(categoria.getId()), categoria)).withRel("Actualizar-parcial")
        );
    }

}
