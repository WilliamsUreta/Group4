package com.ams.Grupo4.controller.controllerV2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ams.Grupo4.Assembler.Usuarioassembler;
import com.ams.Grupo4.Service.UsuarioService;
import com.ams.Grupo4.model.Usuario;

@RestController
@RequestMapping("/api/v2/Usuario")
public class UsuarioControllerV2 {

    @Autowired
        private UsuarioService usuarioService;

    @Autowired
        private Usuarioassembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> GetAllUsuario(){
        List<EntityModel<Usuario>> usuario = usuarioService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (usuario.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            usuario,
            linkTo(methodOn(UsuarioControllerV2.class).GetAllUsuario()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> getUsuarioByid(@PathVariable Integer id){
        Usuario usuario = usuarioService.findById(Long.valueOf(id));          
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(usuario));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> CreateUsuario(@RequestBody Usuario usuario){
        Usuario newUsuario = usuarioService.guardar(usuario);
        return ResponseEntity
                .created(linkTo(methodOn(UsuarioControllerV2.class).getUsuarioByid(newUsuario.getId())).toUri())
                //.created(linkTo(methodOn(ProductoControllerV2.class).GetProductoById(newProducto.getId())).toUri())
                .body(assembler.toModel(newUsuario));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> UpdateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
        usuario.setId(id);
        Usuario UpdateUsuario = usuarioService.guardar(usuario);
        return ResponseEntity.ok(assembler.toModel(UpdateUsuario));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> PatchUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario UpdateUsuario = usuarioService.patchUsuario(id, usuario);
        if (UpdateUsuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(UpdateUsuario));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> DeleteUsuario (@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
