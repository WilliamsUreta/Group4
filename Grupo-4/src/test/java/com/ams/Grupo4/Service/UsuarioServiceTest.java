package com.ams.Grupo4.Service;

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

import com.ams.Grupo4.model.TipoUsuario;
import com.ams.Grupo4.model.Usuario;
import com.ams.Grupo4.repository.UsuarioRepository;

@ActiveProfiles("test")
@SpringBootTest
public class UsuarioServiceTest {
@Autowired
private UsuarioService usuarioService;

@MockBean
private UsuarioRepository usuarioRepository;

private Usuario createUsuario() {
    return new Usuario(
        1, 
        "Ariel",
        "Ariel@example.com",
        "123231",
        new TipoUsuario()
    );
}

@Test
public void testObtenerTodos() {
    when(usuarioRepository.findAll()).thenReturn(java.util.List.of(createUsuario()));
    java.util.List<Usuario> usuarios = usuarioService.obtenerTodos();
    assertNotNull(usuarios);
    assertEquals(1, usuarios.size());
}

@Test
public void testObtenerPorId() {
    when(usuarioRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(createUsuario()));
    Usuario usuario = usuarioService.findById(Long.valueOf(1));
    assertNotNull(usuario);
    assertEquals("Ariel", usuario.getNombre()); // Ajusta según campos reales de Usuario
}

@Test
public void testGuardar() {
    Usuario usuario = createUsuario();
    when(usuarioRepository.save(usuario)).thenReturn(usuario);
    Usuario savedUsuario = usuarioService.guardar(usuario);
    assertNotNull(savedUsuario);
    assertEquals("Ariel", savedUsuario.getNombre()); // Ajusta según campos reales de Usuario
}

@Test
public void testPatchUsuario() {
    Usuario existingUsuario = createUsuario();
    Usuario patchUsuario = new Usuario();
    patchUsuario.setId(1);

    when(usuarioRepository.findById(Long.valueOf(1))).thenReturn(java.util.Optional.of(existingUsuario));
    when(usuarioRepository.save(any(Usuario.class))).thenReturn(existingUsuario);

    Usuario patchedUsuario = usuarioService.patchUsuario(Long.valueOf(1), patchUsuario);
    assertNotNull(patchedUsuario);
    assertEquals(1, patchedUsuario.getId());
}

@Test
public void testEliminar() {
    doNothing().when(usuarioRepository).deleteById(Long.valueOf(1));
    usuarioService.eliminar(Long.valueOf(1));
    verify(usuarioRepository, times(1)).deleteById(Long.valueOf(1));
}

}
