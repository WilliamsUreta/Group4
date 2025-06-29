package com.ams.Grupo4.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.controller.controllerV2.UsuarioControllerV2;
import com.ams.Grupo4.model.Usuario;

@Component
public class Usuarioassembler implements RepresentationModelAssembler<Usuario, EntityModel <Usuario>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel <Usuario> toModel (Usuario usuario){
        return EntityModel.of(usuario,
        linkTo(methodOn(UsuarioControllerV2.class).getUsuarioByid(usuario.getId())).withSelfRel(),
        linkTo(methodOn(UsuarioControllerV2.class).getUsuarioByid(usuario.getId())).withRel("Usuario"),
        linkTo(methodOn(UsuarioControllerV2.class).UpdateUsuario(usuario.getId(), usuario)).withRel("Actualizar"),
        linkTo(methodOn(UsuarioControllerV2.class).DeleteUsuario(Long.valueOf(usuario.getId()))).withRel("Eliminar"),
        linkTo(methodOn(UsuarioControllerV2.class).PatchUsuario(Long.valueOf(usuario.getId()), usuario)).withRel("Actualizar-Parcial")
        );

    }
}
