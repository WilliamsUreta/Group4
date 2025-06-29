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

import com.ams.Grupo4.Assembler.TipoUsuarioassembler;
import com.ams.Grupo4.Service.TipoUsuarioService;
import com.ams.Grupo4.model.TipoUsuario;

@RestController
@RequestMapping("/api/v2/TipoUsuario")
public class TipoUsuarioControllerV2 {

    @Autowired
        private TipoUsuarioService tipoUsuarioService;

    @Autowired
        private TipoUsuarioassembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<TipoUsuario>>> GetAllTipoUsuario(){
        List<EntityModel<TipoUsuario>> tipoUsuario = tipoUsuarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (tipoUsuario.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            tipoUsuario,
            linkTo(methodOn(TipoUsuarioControllerV2.class).GetAllTipoUsuario()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TipoUsuario>> getTipoUsuarioByid(@PathVariable Integer id){
        TipoUsuario tipoUsuario = tipoUsuarioService.findById(id);          
        if (tipoUsuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(tipoUsuario));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TipoUsuario>> CreateTipoUsuario(@RequestBody TipoUsuario tipoUsuario){
        TipoUsuario newTipoUsuario = tipoUsuarioService.guardar(tipoUsuario);
        return ResponseEntity
                .created(linkTo(methodOn(TipoUsuarioControllerV2.class).getTipoUsuarioByid(newTipoUsuario.getId())).toUri())
                //.created(linkTo(methodOn(ProductoControllerV2.class).GetProductoById(newProducto.getId())).toUri())
                .body(assembler.toModel(newTipoUsuario));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TipoUsuario>> UpdateTipoUsuario(@PathVariable Integer id, @RequestBody TipoUsuario tipoUsuario){
        tipoUsuario.setId(id);
        TipoUsuario UpdateTipoUsuario = tipoUsuarioService.guardar(tipoUsuario);
        return ResponseEntity.ok(assembler.toModel(UpdateTipoUsuario));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TipoUsuario>> PatchTipoUsuario(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario){
        TipoUsuario UpdateTipoUsuario = tipoUsuarioService.actualizar(id, tipoUsuario);
        if (UpdateTipoUsuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(UpdateTipoUsuario));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> DeleteTipoUsuario (@PathVariable Long id){
        TipoUsuario tipoUsuario = tipoUsuarioService.findById(id);
        if(tipoUsuario == null){
            return ResponseEntity.notFound().build();
        }
        tipoUsuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
