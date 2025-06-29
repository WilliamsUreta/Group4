package com.ams.Grupo4.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.controller.controllerV2.EstadoDeVentaControllerV2;
import com.ams.Grupo4.model.EstadoDeVenta;

@Component
public class  EstadoDeVentaAssembler implements RepresentationModelAssembler<EstadoDeVenta, EntityModel <EstadoDeVenta>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel <EstadoDeVenta> toModel (EstadoDeVenta estadoDeVenta){
        return EntityModel.of(estadoDeVenta,
        linkTo(methodOn(EstadoDeVentaControllerV2.class).getEstadoDeVentaById(Long.valueOf(estadoDeVenta.getId()))).withSelfRel(),
        linkTo(methodOn(EstadoDeVentaControllerV2.class).getAllEstadosDeVenta()).withRel("Categorias"),
        linkTo(methodOn(EstadoDeVentaControllerV2.class).updateEstadoDeVenta(estadoDeVenta.getId(), estadoDeVenta)).withRel("Actualizar"),
        linkTo(methodOn(EstadoDeVentaControllerV2.class).deleteEstadoDeVenta(Long.valueOf(estadoDeVenta.getId()))).withRel("Eliminar"),
        linkTo(methodOn(EstadoDeVentaControllerV2.class).patchEstadoDeVenta(Long.valueOf(estadoDeVenta.getId()), estadoDeVenta)).withRel("Actualizar-parcial")
        );

    }
}
