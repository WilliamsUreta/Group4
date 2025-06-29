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

import com.ams.Grupo4.Assembler.Productoassembler;
import com.ams.Grupo4.Service.ProductoService;
import com.ams.Grupo4.model.Producto;

@RestController
@RequestMapping("/api/v2/producto")
public class ProductoControllerV2 {

    @Autowired
        private ProductoService productoService;

    @Autowired
        private Productoassembler assembler;
    
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> GetAllProducto(){
        List<EntityModel<Producto>> productos = productoService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            productos,
            linkTo(methodOn(ProductoControllerV2.class).GetAllProducto()).withSelfRel()
        ));
    } 

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> GetProductoById(@PathVariable Integer id){
        Producto producto = productoService.obtenerPorId(Long.valueOf(id));          
        if (producto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(producto));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> CreateProducto(@RequestBody Producto producto){
        Producto newProducto = productoService.guardar(producto);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoControllerV2.class).GetProductoById(newProducto.getId())).toUri())
                .body(assembler.toModel(newProducto));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> UpdateProducto(@PathVariable Integer id, @RequestBody Producto producto){
        producto.setId(id);
        Producto UpdateProducto = productoService.guardar(producto);
        return ResponseEntity.ok(assembler.toModel(UpdateProducto));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> PatchProducto(@PathVariable Long id, @RequestBody Producto producto){
        Producto UpdateProducto = productoService.actualizar(id, producto);
        if (UpdateProducto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(UpdateProducto));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> DeleteProducto (@PathVariable Long id){
        Producto producto = productoService.obtenerPorId(id);
        if(producto == null){
            return ResponseEntity.notFound().build();
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
