package com.ams.Grupo4.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.controller.controllerV2.EstadoDeVentaControllerV2;
import com.ams.Grupo4.model.EstadoDeVenta;

@Component
public class EstadoDeVentaModelAssembler implements RepresentationModelAssembler<EstadoDeVenta, EntityModel<EstadoDeVenta>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<EstadoDeVenta> toModel(EstadoDeVenta estadoDeVenta){
        return EntityModel.of(estadoDeVenta,
        linkTo(methodOn(EstadoDeVentaControllerV2.class).getEstadoDeVentaById(Long.valueOf(estadoDeVenta.getId()))).withSelfRel(),
        linkTo(methodOn(EstadoDeVentaControllerV2.class).getAllEstadosDeVenta()).withRel("Estados de Venta"));
    }
}
