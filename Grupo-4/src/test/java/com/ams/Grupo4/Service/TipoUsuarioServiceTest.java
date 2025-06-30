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
import com.ams.Grupo4.model.TipoUsuario;
import com.ams.Grupo4.model.Ventas;
import com.ams.Grupo4.repository.TipoUsuarioRepository;


@ActiveProfiles("test")
@SpringBootTest
public class TipoUsuarioServiceTest {

    @Autowired
private TipoUsuarioService tipoUsuarioService;

@MockBean
private TipoUsuarioRepository tipoUsuarioRepository;

private TipoUsuario createTipoUsuario() {
    return new TipoUsuario(
        1, 
        "Bastian" 
    );
}

@Test
public void testObtenerTodos() {
    when(tipoUsuarioRepository.findAll()).thenReturn(List.of(createTipoUsuario()));
    List<TipoUsuario> tipoUsuarios = tipoUsuarioService.findAll();
    assertNotNull(tipoUsuarios);
    assertEquals(1, tipoUsuarios.size());
}

@Test
public void testObtenerPorId() {
    when(tipoUsuarioRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(createTipoUsuario()));
    TipoUsuario tipoUsuario = tipoUsuarioService.findById(1);
    assertNotNull(tipoUsuario);
    assertEquals("Cliente", tipoUsuario.getNombre()); // Verifica si este campo existe y es correcto
}

@Test
public void testGuardar() {
    TipoUsuario tipoUsuario = createTipoUsuario();
    when(tipoUsuarioRepository.save(tipoUsuario)).thenReturn(tipoUsuario);
    TipoUsuario savedTipoUsuario = tipoUsuarioService.guardar(tipoUsuario);
    assertNotNull(savedTipoUsuario);
    assertEquals("Cliente", savedTipoUsuario.getNombre()); // Verifica si este campo existe y es correcto
}

@Test
public void testPatchTipoUsuario() {
    TipoUsuario existingTipoUsuario = createTipoUsuario();
    TipoUsuario patchTipoUsuario = new TipoUsuario();
    patchTipoUsuario.setId(1);

    when(tipoUsuarioRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(existingTipoUsuario));
    when(tipoUsuarioRepository.save(any(TipoUsuario.class))).thenReturn(existingTipoUsuario);

    TipoUsuario patchedTipoUsuario = tipoUsuarioService.actualizar(Long.valueOf(1), patchTipoUsuario);
    assertNotNull(patchedTipoUsuario);
    assertEquals(1, patchedTipoUsuario.getId());
}

@Test
public void testEliminar() {
    doNothing().when(tipoUsuarioRepository).deleteById(Long.valueOf(1));
    tipoUsuarioService.deleteById(Long.valueOf(1));
    verify(tipoUsuarioRepository, times(1)).deleteById(Long.valueOf(1));
}

}
