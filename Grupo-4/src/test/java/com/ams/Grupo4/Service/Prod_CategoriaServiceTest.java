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

import com.ams.Grupo4.model.Categoria;
import com.ams.Grupo4.model.Prod_Categoria;
import com.ams.Grupo4.model.Producto;
import com.ams.Grupo4.repository.Prod_CategoriaRepository;

@ActiveProfiles("test")
@SpringBootTest
public class Prod_CategoriaServiceTest {

    @Autowired
    private Prod_CategoriaService prod_CategoriaService;

    @MockBean
    private Prod_CategoriaRepository prod_CategoriaRepository;

    private Prod_Categoria createProd_Categoria(){
        return new Prod_Categoria(
            1,
            new Categoria(),
            new Producto()             
        );
    }

    @Test
    public void testobtenerTodos() {
        when(prod_CategoriaRepository.findAll()).thenReturn(List.of(createProd_Categoria()));
        List<Prod_Categoria> prod_Categorias = prod_CategoriaService.obtenerTodos();
        assertNotNull(prod_Categorias);
        assertEquals(1, prod_Categorias.size());
    }

    @Test
    public void testobtenerPorId() {
        when(prod_CategoriaRepository.findById(1l)).thenReturn(java.util.Optional.of(createProd_Categoria()));
        Prod_Categoria prod_Categoria = prod_CategoriaService.obtenerPorId(Long.valueOf(1));
        assertNotNull(prod_Categoria);
        assertEquals(1, prod_Categoria.getId());
    }

    @Test
    public void testguardar() {
        Prod_Categoria prod_Categoria = createProd_Categoria();
        when(prod_CategoriaRepository.save(prod_Categoria)).thenReturn(prod_Categoria);
        Prod_Categoria savedProd_Categoria = prod_CategoriaService.guardar(prod_Categoria);
        assertNotNull(savedProd_Categoria);
        assertEquals(1, savedProd_Categoria.getId());
    }

    @Test
    public void testactualizar() {
        Prod_Categoria existingProd_Categoria = createProd_Categoria();
        Prod_Categoria actualizar = new Prod_Categoria();
        actualizar.setId(1);

        when(prod_CategoriaRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(existingProd_Categoria));
        when(prod_CategoriaRepository.save(any(Prod_Categoria.class))).thenReturn(existingProd_Categoria);

        Prod_Categoria Prod_CategoriaActuactualizado = prod_CategoriaService.actualizar(1l, actualizar);
        assertNotNull(Prod_CategoriaActuactualizado);
        assertEquals(3, Prod_CategoriaActuactualizado.getId());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(prod_CategoriaRepository).deleteById(1l);
        prod_CategoriaService.eliminar(Long.valueOf(1));
        verify(prod_CategoriaRepository, times(1)).deleteById(Long.valueOf(1));
    }
}
