package com.ams.Grupo4.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.controller.controllerV2.MetodoPagoControllerV2;
import com.ams.Grupo4.model.MetodoPago;

@Component
public class MetodoPagoAssembler implements RepresentationModelAssembler<MetodoPago, EntityModel <MetodoPago>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel <MetodoPago> toModel (MetodoPago metodoPago){
        return EntityModel.of(metodoPago,
        linkTo(methodOn(MetodoPagoControllerV2.class).GetMetodoPagoId(Long.valueOf(metodoPago.getId()))).withSelfRel(),
        linkTo(methodOn(MetodoPagoControllerV2.class).GetAllMetodoPago()).withRel("MetodoPago"),
        linkTo(methodOn(MetodoPagoControllerV2.class).UpdateMetodoPago(metodoPago.getId(), metodoPago)).withRel("Actualizar"),
        linkTo(methodOn(MetodoPagoControllerV2.class).PatchMetodoPago(Long.valueOf(metodoPago.getId()), metodoPago)).withRel("Actualizar-parcial"),
        linkTo(methodOn(MetodoPagoControllerV2.class).DeleteMetodoPago(Long.valueOf(metodoPago.getId()))).withRel("Eliminar")
        );
    }
}
