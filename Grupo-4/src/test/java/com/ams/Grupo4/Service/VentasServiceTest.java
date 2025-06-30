package com.ams.Grupo4.Service;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.ams.Grupo4.model.EstadoDeVenta;
import com.ams.Grupo4.model.MetodoPago;
import com.ams.Grupo4.model.Ventas;
import com.ams.Grupo4.repository.VentasRepository;

@ActiveProfiles("test")
@SpringBootTest
public class VentasServiceTest {

@Autowired
private VentasService ventasService;

@MockBean
private VentasRepository ventasRepository;

private Ventas createVentas() {
    return new Ventas(
        1,
        LocalDate.now(), 
        LocalTime.now(),
        6,
        new MetodoPago(),
        new EstadoDeVenta()
    );
}

@Test
public void testObtenerTodos() {
    when(ventasRepository.findAll()).thenReturn(java.util.List.of(createVentas()));
    java.util.List<Ventas> ventas = ventasService.obtenerTodos();
    assertNotNull(ventas);
    assertEquals(1, ventas.size());
}

@Test
public void testObtenerPorId() {
    when(ventasRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(createVentas()));
    Ventas venta = ventasService.obtenerPorId(Long.valueOf(1));
    assertNotNull(venta);
    assertEquals(6, venta.getTotalVenta()); 
}

@Test
public void testGuardar() {
    Ventas venta = createVentas();
    when(ventasRepository.save(venta)).thenReturn(venta);
    Ventas savedVenta = ventasService.guardar(venta);
    assertNotNull(savedVenta);
    assertEquals(6, savedVenta.getTotalVenta());
}

@Test
public void testPatchVentas() {
    Ventas existingVentas = createVentas();
    Ventas patchVentas = new Ventas();
    patchVentas.setId(1);

    when(ventasRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(existingVentas));
    when(ventasRepository.save(any(Ventas.class))).thenReturn(existingVentas);

    Ventas patchedVentas = ventasService.actualizar(Long.valueOf(1), patchVentas);
    assertNotNull(patchedVentas);
    assertEquals(1, patchedVentas.getId());
}

@Test
public void testEliminar() {
    doNothing().when(ventasRepository).deleteById(Long.valueOf(1));
    ventasService.eliminar(Long.valueOf(1));
    verify(ventasRepository, times(1)).deleteById(Long.valueOf(1));
}



}
