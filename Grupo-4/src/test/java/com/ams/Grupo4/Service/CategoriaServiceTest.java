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

import com.ams.Grupo4.Service.CategoriaService;
import com.ams.Grupo4.model.Categoria;
import com.ams.Grupo4.repository.CategoriaRepository;


@SpringBootTest
public class CategoriaServiceTest {
    @Autowired
    private CategoriaService categoriaService;

    @MockBean
    private CategoriaRepository categoriaRepository;

    private Categoria createCategoria(){
        return new Categoria(1, "Legumbres");
    }

    @Test
    public void testFindAll(){
        when(categoriaRepository.findAll()).thenReturn(List.of(createCategoria()));
        List<Categoria> categorias = categoriaService.obtenerCategorias();
        assertNotNull(categorias);
        assertEquals(1,categorias.size());
    }

    @Test
    public void testFindById(){
        when(categoriaRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(createCategoria()));
        Categoria categoria = categoriaService.obtenerCategoriaPorId(Long.valueOf(1));
        assertNotNull(categoria);
        assertEquals("Legumbres",categoria.getCategoria());
    }

    @Test
    public void testGuardarCategoria(){
        Categoria categoria = createCategoria();
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        Categoria guardarCategoria = categoriaService.guardarCategoria(categoria);
        assertNotNull(guardarCategoria);
        assertEquals("Legumbre",guardarCategoria.getCategoria());
    }

    @Test
    public void actualizarCategoria(){
        Categoria categoriaExistente = createCategoria();
        Categoria patchData = new Categoria();
        patchData.setCategoria("Lacteos");

        when(categoriaRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(categoriaExistente));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaExistente);

        Categoria patchCategoria = categoriaService.actualizar(Long.valueOf(1), patchData);
        assertNotNull(patchCategoria);
        assertEquals("Lacteos", patchCategoria.getCategoria());
    }

    @Test
    public void testEliminarCategoria(){
        doNothing().when(categoriaRepository).deleteById(Long.valueOf(1));
        categoriaService.eliminarCategoria(Long.valueOf(1));
        verify(categoriaRepository, times(1)).deleteById(Long.valueOf(1));
    }
}
