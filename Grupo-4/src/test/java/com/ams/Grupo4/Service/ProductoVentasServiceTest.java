package com.ams.Grupo4.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;  

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.ams.Grupo4.model.Producto;
import com.ams.Grupo4.model.ProductosVentas;
import com.ams.Grupo4.model.Ventas;
import com.ams.Grupo4.repository.ProductoRepository;
import com.ams.Grupo4.repository.ProductosVentasRepository;

@ActiveProfiles("test")
@SpringBootTest
public class ProductoVentasServiceTest {

    @Autowired
    private ProductoVentasService productoVentasService;

    @MockBean
    private ProductosVentasRepository productosVentasRepository;

    private ProductosVentas createProductoVentas() {
        return new ProductosVentas(
            1, 
            new Producto(),
            new Ventas()
        );
    }

    @Test
    public void testobtenerTodos() {
        when(productosVentasRepository.findAll()).thenReturn(List.of(createProductoVentas()));
        List<ProductosVentas> productosVentas = productoVentasService.obtenerTodos();
        assertNotNull(productosVentas);
        assertEquals(1, productosVentas.size());
    }

    @Test
    public void testobtenerPorId() {
        when(productosVentasRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(createProductoVentas()));
        ProductosVentas productosVenta = productoVentasService.obtenerPorId(Long.valueOf(1));
        assertNotNull(productosVenta);
        assertEquals("Manzana", productosVenta.getProducto());
    }

    @Test
    public void testguardar() {
        ProductosVentas productosVenta = createProductoVentas();
        when(productosVentasRepository.save(productosVenta)).thenReturn(productosVenta);
        ProductosVentas savedProductosVentas = productoVentasService.guardar(productosVenta);
        assertNotNull(savedProductosVentas);
        assertEquals("Mesa", savedProductosVentas.getProducto());
    }

    @Test
    public void testpatchProductV() {
        ProductosVentas existingProductosVentas = createProductoVentas();
        ProductosVentas patchProductV = new ProductosVentas();
        patchProductV.setId(1);

        when(productosVentasRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(existingProductosVentas));
        when(productosVentasRepository.save(any(ProductosVentas.class))).thenReturn(existingProductosVentas);

        ProductosVentas patchedProductosVentas = productoVentasService.patchProductV(Long.valueOf(1), patchProductV);
        assertNotNull(patchedProductosVentas);
        assertEquals(1, patchedProductosVentas.getId());
    }

    @Test
    public void testeliminar() {
        doNothing().when(productosVentasRepository).deleteById(Long.valueOf(1));
        productoVentasService.eliminar(Long.valueOf(1));
        verify(productosVentasRepository, times(1)).deleteById(Long.valueOf(1));
        
    }


}
