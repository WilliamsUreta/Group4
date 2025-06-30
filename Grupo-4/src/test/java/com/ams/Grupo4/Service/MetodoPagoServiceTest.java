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

import com.ams.Grupo4.model.MetodoPago;
import com.ams.Grupo4.repository.MetodoPagoRepository;

@ActiveProfiles("test")
@SpringBootTest
public class MetodoPagoServiceTest {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @MockBean
    private MetodoPagoRepository metodoPagoRepository;

    private MetodoPago createMetodoPago(){
        return new MetodoPago(1,"Debito");
    }

    @Test
    public void testObtenerTodos() { // garantizamos que la funcionalidad de consulta de categorías esté funcionando correctamente
        when(metodoPagoRepository.findAll()).thenReturn(List.of(createMetodoPago()));
        List<MetodoPago> metodoPagos = metodoPagoService.ObtenerTodos();
        assertNotNull(metodoPagos); // aqui verificamos que la lista no sea null
        assertEquals(1, metodoPagos.size());//verificamos que la lista tenga 1 elemento
    }

    @Test
    public void testObtenerPorId() { 
        when(metodoPagoRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(createMetodoPago()));// cuando se llame a findById(1L), devuelva una categoría existente
        MetodoPago metodoPago = metodoPagoService.ObtenerPorId(Long.valueOf(1));
        assertNotNull(metodoPago); // Asegura que la categoría no sea null
        assertEquals("Debito", metodoPago.getMetodoPago()); // Valida que el nombre sea correcto
    }

    @Test
    public void testGuardar() {  
        MetodoPago metodoPago = createMetodoPago(); // Crea una instancia ficticia de categoria que devuelve una categoría predefinida con datos
        when(metodoPagoRepository.save(metodoPago)).thenReturn(metodoPago); // devuelve la misma categoría como si hubiera sido guardada correctamente
        MetodoPago MetodoPagoGuardado = metodoPagoService.Guardar(metodoPago);
        assertNotNull(MetodoPagoGuardado); 
        assertEquals("Motocross", MetodoPagoGuardado.getMetodoPago()); //Comprueba que el nombre de la categoría sea guardada
    }

    @Test
    public void testActualizar() {
        MetodoPago existingMetodoPago = createMetodoPago(); 
        MetodoPago Actualizar = new MetodoPago(); 
        Actualizar.setMetodoPago("Debito");

        when(metodoPagoRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(existingMetodoPago));
        when(metodoPagoRepository.save(any(MetodoPago.class))).thenReturn(existingMetodoPago);
                                                                                 

        MetodoPago MetodoPagoActualizado = metodoPagoService.Actualizar(Long.valueOf(1), Actualizar); 
        assertNotNull(MetodoPagoActualizado);
        assertEquals("Debito", MetodoPagoActualizado.getMetodoPago()); 
    }
    @Test
    public void testEliminar() { 
        doNothing().when(metodoPagoRepository).deleteById(1L); 
        metodoPagoService.Eliminar(Long.valueOf(1));
        verify(metodoPagoRepository, times(1)).deleteById(Long.valueOf(1)); 
    }
}
