package com.ams.Grupo4.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.controller.controllerV2.TipoUsuarioControllerV2;
import com.ams.Grupo4.model.TipoUsuario;

@Component
public class TipoUsuarioassembler implements RepresentationModelAssembler<TipoUsuario, EntityModel <TipoUsuario>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel <TipoUsuario> toModel (TipoUsuario tipoUsuario){
        return EntityModel.of(tipoUsuario,
        linkTo(methodOn(TipoUsuarioControllerV2.class).getTipoUsuarioByid(tipoUsuario.getId())).withSelfRel(),
        linkTo(methodOn(TipoUsuarioControllerV2.class).GetAllTipoUsuario()).withRel("Tipo_Usuario"),
        linkTo(methodOn(TipoUsuarioControllerV2.class).UpdateTipoUsuario(tipoUsuario.getId(), tipoUsuario)).withRel("Actualizar"),
        linkTo(methodOn(TipoUsuarioControllerV2.class).DeleteTipoUsuario(Long.valueOf(tipoUsuario.getId()))).withRel("Eliminar"),
        linkTo(methodOn(TipoUsuarioControllerV2.class).PatchTipoUsuario(Long.valueOf(tipoUsuario.getId()), tipoUsuario)).withRel("Actualizar-Parcial")
        );

    }
}
