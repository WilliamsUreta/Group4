package com.ams.Grupo4.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;  

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.ams.Grupo4.model.EstadoDeVenta;
import com.ams.Grupo4.repository.EstadoDeVentaRepository;

@ActiveProfiles("test")
@SpringBootTest
public class EstadoDeVentaServicioTest {

    @Autowired
    private EstadoDeVentaServicio estadoDeVentaServicio ;

    @MockBean
    private EstadoDeVentaRepository estadoDeVentaRepository ;

    private EstadoDeVenta createEstadoDeVenta(){
        return new EstadoDeVenta(
        1,   
        "vendido"
        );
    }

    public void testObtenerTodos(){
        when(estadoDeVentaRepository.findAll()).thenReturn(List.of(createEstadoDeVenta()));
        List<EstadoDeVenta> estadoDeVentas = estadoDeVentaServicio.ObtenerTodos();
        assertNotNull(estadoDeVentas);
        assertEquals(1,estadoDeVentas.size());
    }

    @Test
    public void testObtenerPorId(){
        when(estadoDeVentaRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(createEstadoDeVenta()));
        EstadoDeVenta estadoDeVenta = estadoDeVentaServicio.ObtenerPorId(Long.valueOf(1));
        assertNotNull(estadoDeVenta); // Asegura que la categoría no sea null
        assertEquals("vendido", estadoDeVenta.getEstado());
    }

     @Test
    public void testGuardar(){
        EstadoDeVenta estadoDeVenta = createEstadoDeVenta();
        when(estadoDeVentaRepository.save(estadoDeVenta)).thenReturn(estadoDeVenta);
        EstadoDeVenta sevedEstadoDeVenta = estadoDeVentaServicio.Guardar(estadoDeVenta);
        assertNotNull(sevedEstadoDeVenta);
        assertEquals("vendido", sevedEstadoDeVenta.getEstado());
    }

    
    @Test
    public void testActualizar() {
        EstadoDeVenta existingEstadoDeVenta = createEstadoDeVenta(); 
        EstadoDeVenta Actualizar = new EstadoDeVenta(); 
        Actualizar.setEstado("vendido");

        when(estadoDeVentaRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(existingEstadoDeVenta));
        when(estadoDeVentaRepository.save(any(EstadoDeVenta.class))).thenReturn(existingEstadoDeVenta);
                                                                                 


        EstadoDeVenta estadoDeVentaActualizado = estadoDeVentaServicio.Actualizar(Long.valueOf(1), Actualizar); 
        assertNotNull(estadoDeVentaActualizado);
        assertEquals("vendido", estadoDeVentaActualizado.getEstado()); 
    }

    @Test
    public void testEliminard() { 
        doNothing().when(estadoDeVentaRepository).deleteById(1l); 
        estadoDeVentaServicio.Eliminar(Long.valueOf(1));
        verify(estadoDeVentaRepository, times(1)).deleteById(Long.valueOf(1)); 
    }
     
}
