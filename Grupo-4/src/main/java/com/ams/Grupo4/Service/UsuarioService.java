package com.ams.Grupo4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.Grupo4.model.Usuario;
import com.ams.Grupo4.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    //Obtener un usuario por su ID
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    //Guardar un nuevo usuario
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //Eliminar un usuario por su ID
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        usuarioRepository.delete(usuario);
    }

    //Patch Usser
    public Usuario patchUsuario(Long id, Usuario usuario){
        Usuario usuarioE = findById(id);
        if (usuarioE != null){
            if (usuario.getNombre() != null){
                usuarioE.setNombre(usuario.getNombre());
            }
            if (usuario.getCorreo() != null){
                usuarioE.setCorreo(usuario.getCorreo());
            }
            if (usuario.getContraseña() != null){
                usuarioE.setContraseña(usuario.getContraseña());
            }
            if (usuario.getTipoUsuario() != null){
                usuarioE.setTipoUsuario(usuario.getTipoUsuario());
            }
            return guardar(usuarioE);
        }
        return null;
    }

    //Buscar usuarios por tipo de usuario
    public List<Usuario> buscarPorTipoUsuario(Long tipoUsuarioId) {
        return usuarioRepository.findByTipoUsuarioId(tipoUsuarioId);
    }

    //Buscar usuarios por nombre
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombreContaining(nombre);
    }
}
