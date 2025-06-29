package com.ams.Grupo4.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.controller.controllerV2.Prod_CategoriaControllerV2;
import com.ams.Grupo4.model.Prod_Categoria;

@Component
public class Prod_CategoriaAssembler implements RepresentationModelAssembler<Prod_Categoria, EntityModel <Prod_Categoria>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel <Prod_Categoria> toModel (Prod_Categoria prod_Categoria){
        return EntityModel.of(prod_Categoria,
        linkTo(methodOn(Prod_CategoriaControllerV2.class).getProd_CategoriaByid(Long.valueOf(prod_Categoria.getId()))).withSelfRel(),
        linkTo(methodOn(Prod_CategoriaControllerV2.class).GetAllProd_Categoria()).withRel("Prod_Categoria"),
        linkTo(methodOn(Prod_CategoriaControllerV2.class).UpdateProd_Categoria(Long.valueOf(prod_Categoria.getId()), prod_Categoria)).withRel("Actualizar"),
        linkTo(methodOn(Prod_CategoriaControllerV2.class).DeleteProd_Categoria(Long.valueOf(prod_Categoria.getId()))).withRel("Eliminar"),
        linkTo(methodOn(Prod_CategoriaControllerV2.class).PatchProd_Categoria(Long.valueOf(prod_Categoria.getId()), prod_Categoria)).withRel("Actualizacion parcial")
        );

    }
}
