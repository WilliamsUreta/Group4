package com.ams.Grupo4.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.model.ProductosVentas;
import com.ams.Grupo4.controller.controllerV2.ProductoVentasControllerV2;

@Component
public class ProductoVentasassembler implements RepresentationModelAssembler<ProductosVentas, EntityModel <ProductosVentas>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel <ProductosVentas> toModel (ProductosVentas productosVentas){
        return EntityModel.of(productosVentas,
        linkTo(methodOn(ProductoVentasControllerV2.class).getProductoVentasByid(productosVentas.getId())).withSelfRel(),
        linkTo(methodOn(ProductoVentasControllerV2.class).GetAllProductoVentas()).withRel("ProductoVentas"),
        linkTo(methodOn(ProductoVentasControllerV2.class).UpdateProductosVentas(productosVentas.getId(), productosVentas)).withRel("Actualizar"),
        linkTo(methodOn(ProductoVentasControllerV2.class).DeleteProductosVentas(Long.valueOf(productosVentas.getId()))).withRel("Eliminar"),
        linkTo(methodOn(ProductoVentasControllerV2.class).PatchProductosVentas(Long.valueOf(productosVentas.getId()), productosVentas)).withRel("Actualizar-Parcial")
        );

    }
}
