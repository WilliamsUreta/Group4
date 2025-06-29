package com.ams.Grupo4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.Grupo4.model.TipoUsuario;
import com.ams.Grupo4.model.Usuario;
import com.ams.Grupo4.repository.TipoUsuarioRepository;
import com.ams.Grupo4.repository.UsuarioRepository;

import java.util.List;

@Service
public class TipoUsuarioService {
    
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    //Obtener todos los tipos de usuario
    public List<TipoUsuario> findAll() {
        return tipoUsuarioRepository.findAll();
    }

    //Obtener un tipo de usuario por su ID
    public TipoUsuario findById(long id) {
        return tipoUsuarioRepository.findById(id).orElse(null);
    }

    //Buscar tipo de usuario por nombre
    public List<TipoUsuario> buscarPorNombre(String nombre) {
        return tipoUsuarioRepository.findByNombre(nombre);
    }

    //Guardar un nuevo tipo de usuario
    public TipoUsuario guardar(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    //Actualizar un tipo de usuario existente
    public TipoUsuario actualizar(Long id, TipoUsuario tipoUsuario) {
        TipoUsuario tipoUserE = findById(id);
        if (tipoUserE != null) {
            if(tipoUserE.getNombre() != null){
                tipoUserE.setNombre(tipoUsuario.getNombre());
            }
        }
        return null;
    }

    //Eliminar un tipo de usuario por su ID
    public void deleteById(Long id) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Tipo de usuario no encontrado"));

        List<Usuario> usuarios = usuarioRepository.findByTipoUsuario(tipoUsuario);

        for (Usuario usuario : usuarios){
            usuarioService.eliminar(Long.valueOf(usuario.getId()));
        }

        tipoUsuarioRepository.delete(tipoUsuario);
    }

}
