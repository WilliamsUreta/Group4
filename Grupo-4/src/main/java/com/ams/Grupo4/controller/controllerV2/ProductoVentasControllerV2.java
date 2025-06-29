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

import com.ams.Grupo4.Assembler.ProductoVentasassembler;
import com.ams.Grupo4.Service.ProductoVentasService;
import com.ams.Grupo4.model.ProductosVentas;


@RestController
@RequestMapping("/api/v2/ProductoVentas")
public class ProductoVentasControllerV2 {

    @Autowired
        private ProductoVentasService productoVentasService;

    @Autowired
        private ProductoVentasassembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ProductosVentas>>> GetAllProductoVentas(){
        List<EntityModel<ProductosVentas>> productoVentas = productoVentasService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (productoVentas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
            productoVentas,
            linkTo(methodOn(ProductoVentasControllerV2.class).GetAllProductoVentas()).withSelfRel()
));
    }     

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductosVentas>> getProductoVentasByid(@PathVariable Integer id){
        ProductosVentas productosVentas = productoVentasService.obtenerPorId(Long.valueOf(id));          
        if (productosVentas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(productosVentas));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductosVentas>> CreateProductosVentas(@RequestBody ProductosVentas productosVentas){
        ProductosVentas newProductosVentas = productoVentasService.guardar(productosVentas);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoVentasControllerV2.class).getProductoVentasByid(newProductosVentas.getId())).toUri())
                //.created(linkTo(methodOn(ProductoControllerV2.class).GetProductoById(newProducto.getId())).toUri())
                .body(assembler.toModel(newProductosVentas));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductosVentas>> UpdateProductosVentas(@PathVariable Integer id, @RequestBody ProductosVentas productosVentas){
        productosVentas.setId(id);
        ProductosVentas UpdateProductosVentas = productoVentasService.guardar(productosVentas);
        return ResponseEntity.ok(assembler.toModel(UpdateProductosVentas));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductosVentas>> PatchProductosVentas(@PathVariable Long id, @RequestBody ProductosVentas productosVentas){
        ProductosVentas UpdateProductosVentas = productoVentasService.patchProductV(id, productosVentas);
        if (UpdateProductosVentas == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(UpdateProductosVentas));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> DeleteProductosVentas (@PathVariable Long id){
        ProductosVentas productosVentas = productoVentasService.obtenerPorId(id);
        if(productosVentas == null){
            return ResponseEntity.notFound().build();
        }
        productoVentasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
