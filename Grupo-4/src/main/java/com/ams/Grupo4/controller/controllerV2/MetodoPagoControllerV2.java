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

import com.ams.Grupo4.Assembler.MetodoPagoAssembler;
import com.ams.Grupo4.Service.MetodoPagoService;
import com.ams.Grupo4.model.MetodoPago;

@RestController
@RequestMapping("/api/v2/Metodo-Pago")
public class MetodoPagoControllerV2 {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private MetodoPagoAssembler assembler;

    @GetMapping(produces=MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<MetodoPago>>> GetAllMetodoPago(){
        List<EntityModel<MetodoPago>> metodoPago = metodoPagoService.ObtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if(metodoPago.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            metodoPago,
            linkTo(methodOn(MetodoPagoControllerV2.class).GetAllMetodoPago()).withSelfRel()));
    }

    @GetMapping(value="/{id}",produces=MediaTypes.HAL_FORMS_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoPago>> GetMetodoPagoId(@PathVariable Long id){
        MetodoPago metodoPago = metodoPagoService.ObtenerPorId(id);
        if (metodoPago == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(metodoPago));
    }
    
    @PostMapping(produces=MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoPago>> CreateMetodoPago(@RequestBody MetodoPago metodoPago){
        MetodoPago newMetodoPago = metodoPagoService.Guardar(metodoPago);
        return ResponseEntity
                .created(linkTo(methodOn(MetodoPagoControllerV2.class).GetMetodoPagoId(Long.valueOf(newMetodoPago.getId()))).toUri())
                .body(assembler.toModel(newMetodoPago));
            }
    
    @PutMapping(value="/{id}", produces=MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoPago>> UpdateMetodoPago(@PathVariable Integer id,@RequestBody MetodoPago metodoPago){
        metodoPago.setId(id);
        MetodoPago UpdateMetodoPago = metodoPagoService.Guardar(metodoPago);
        return ResponseEntity.ok(assembler.toModel(UpdateMetodoPago));
    }

    @PatchMapping(value="/{id}", produces=MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoPago>> PatchMetodoPago(@PathVariable Long id, @RequestBody MetodoPago metodoPago){
        MetodoPago UpdateMetodoPago = metodoPagoService.Actualizar(id, metodoPago);
        if (UpdateMetodoPago == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(UpdateMetodoPago));
    }

    @DeleteMapping
    public ResponseEntity<Void> DeleteMetodoPago(@PathVariable Long id){
        if (metodoPagoService.ObtenerPorId(id) == null){
            return ResponseEntity.notFound().build();
        }
        metodoPagoService.Eliminar(id);
        return  ResponseEntity.noContent().build();
    }

}
