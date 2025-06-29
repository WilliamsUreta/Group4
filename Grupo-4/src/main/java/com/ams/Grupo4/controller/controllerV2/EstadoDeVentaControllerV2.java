package com.ams.Grupo4.controller.controllerV2;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ams.Grupo4.Assembler.EstadoDeVentaAssembler;
import com.ams.Grupo4.Service.EstadoDeVentaServicio;
import com.ams.Grupo4.model.EstadoDeVenta;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v2/estadoDeVenta")
public class EstadoDeVentaControllerV2 {

     @Autowired
    private EstadoDeVentaServicio estadoDeVentaServicio;

    @Autowired
    private EstadoDeVentaAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<EstadoDeVenta>>> getAllEstadosDeVenta() {
        List<EntityModel<EstadoDeVenta>> estadosDeVenta = estadoDeVentaServicio.ObtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (estadosDeVenta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
            estadosDeVenta,
            linkTo(methodOn(EstadoDeVentaControllerV2.class).getAllEstadosDeVenta()).withSelfRel()
        ));
    }

    @GetMapping(value="/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<EstadoDeVenta>> getEstadoDeVentaById(@PathVariable Long id) {
        EstadoDeVenta estadoDeVenta = estadoDeVentaServicio.ObtenerPorId(id);
        if (estadoDeVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(estadoDeVenta));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<EstadoDeVenta>> createEstadoDeVenta(@RequestBody EstadoDeVenta estadoDeVenta) {
        EstadoDeVenta newEstadoDeVenta = estadoDeVentaServicio.Guardar(estadoDeVenta);
        return ResponseEntity
                .created(linkTo(methodOn(EstadoDeVentaControllerV2.class).getEstadoDeVentaById(Long.valueOf(newEstadoDeVenta.getId()))).toUri())
                .body(assembler.toModel(newEstadoDeVenta));
    }

    @PutMapping(value="/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<EstadoDeVenta>> updateEstadoDeVenta(@PathVariable Integer id, @RequestBody EstadoDeVenta estadoDeVenta) {
        estadoDeVenta.setId(id);
        EstadoDeVenta updateEstadoDeVenta = estadoDeVentaServicio.Guardar(estadoDeVenta);
        return ResponseEntity.ok(assembler.toModel(updateEstadoDeVenta));
    }

    @PatchMapping(value="/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<EstadoDeVenta>> patchEstadoDeVenta(@PathVariable Long id, @RequestBody EstadoDeVenta estadoDeVenta) {
        EstadoDeVenta updatedEstadoDeVenta = estadoDeVentaServicio.Actualizar(id, estadoDeVenta);
        if (updatedEstadoDeVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedEstadoDeVenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadoDeVenta(@PathVariable Long id) {
        if (estadoDeVentaServicio.ObtenerPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        estadoDeVentaServicio.Eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
