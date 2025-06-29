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

import com.ams.Grupo4.Assembler.Ventasassembler;
import com.ams.Grupo4.Service.VentasService;
import com.ams.Grupo4.model.Ventas;

@RestController
@RequestMapping("/api/v2/Ventas")
public class VentasControllerV2 {

    @Autowired
        private VentasService ventasService;

    @Autowired
        private Ventasassembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Ventas>>> GetAllVentas(){
        List<EntityModel<Ventas>> ventas = ventasService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (ventas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            ventas,
            linkTo(methodOn(VentasControllerV2.class).GetAllVentas()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Ventas>> getVentasByid(@PathVariable Integer id){
        Ventas ventas = ventasService.obtenerPorId(Long.valueOf(id));          
        if (ventas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(ventas));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Ventas>> CreateVentas(@RequestBody Ventas ventas){
        Ventas newVentas = ventasService.guardar(ventas);
        return ResponseEntity
                .created(linkTo(methodOn(VentasControllerV2.class).getVentasByid(newVentas.getId())).toUri())
                //.created(linkTo(methodOn(ProductoControllerV2.class).GetProductoById(newProducto.getId())).toUri())
                .body(assembler.toModel(newVentas));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Ventas>> UpdateVentas(@PathVariable Integer id, @RequestBody Ventas ventas){
        ventas.setId(id);
        Ventas UpdateUsuario = ventasService.guardar(ventas);
        return ResponseEntity.ok(assembler.toModel(UpdateUsuario));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Ventas>> PatchVentas(@PathVariable Long id, @RequestBody Ventas ventas){
        Ventas UpdateVentas = ventasService.actualizar(id, ventas);
        if (UpdateVentas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(UpdateVentas));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> DeleteVentas (@PathVariable Long id){
        Ventas ventas = ventasService.obtenerPorId(id);
        if(ventas == null){
            return ResponseEntity.notFound().build();
        }
        ventasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
