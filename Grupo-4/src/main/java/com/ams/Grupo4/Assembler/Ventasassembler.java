package com.ams.Grupo4.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.controller.controllerV2.VentasControllerV2;
import com.ams.Grupo4.model.Ventas;

@Component
public class Ventasassembler implements RepresentationModelAssembler<Ventas, EntityModel <Ventas>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel <Ventas> toModel (Ventas ventas){
        return EntityModel.of(ventas,
        linkTo(methodOn(VentasControllerV2.class).getVentasByid(ventas.getId())).withSelfRel(),
        linkTo(methodOn(VentasControllerV2.class).GetAllVentas()).withRel("Ventas"),
        linkTo(methodOn(VentasControllerV2.class).UpdateVentas(ventas.getId(), ventas)).withRel("Actualizar"),
        linkTo(methodOn(VentasControllerV2.class).DeleteVentas(Long.valueOf(ventas.getId()))).withRel("Eliminar"),
        linkTo(methodOn(VentasControllerV2.class).PatchVentas(Long.valueOf(ventas.getId()), ventas)).withRel("Actualizar-Parcial")
        );

    }
}
