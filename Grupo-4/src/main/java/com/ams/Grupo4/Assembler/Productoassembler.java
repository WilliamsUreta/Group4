package com.ams.Grupo4.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.controller.controllerV2.ProductoControllerV2;
import com.ams.Grupo4.model.Producto;


@Component
public class Productoassembler implements RepresentationModelAssembler<Producto, EntityModel <Producto>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel <Producto> toModel (Producto producto){
        return EntityModel.of(producto,
        linkTo(methodOn(ProductoControllerV2.class).GetProductoById(producto.getId())).withSelfRel(),
        linkTo(methodOn(ProductoControllerV2.class).GetAllProducto()).withRel("Producto"),
        linkTo(methodOn(ProductoControllerV2.class).UpdateProducto(producto.getId(), producto)).withRel("Actualizar"),
        linkTo(methodOn(ProductoControllerV2.class).DeleteProducto(Long.valueOf(producto.getId()))).withRel("Eliminar"),
        linkTo(methodOn(ProductoControllerV2.class).PatchProducto(Long.valueOf(producto.getId()), producto)).withRel("Actualizar-Parcial")
        
        );

    }
}
