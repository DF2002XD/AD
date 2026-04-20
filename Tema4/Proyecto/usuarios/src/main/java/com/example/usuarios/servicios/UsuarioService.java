package com.example.usuarios.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.usuarios.entidades.Usuario;
import com.example.usuarios.entidades.UsuarioDTO;
import com.example.usuarios.entidades.UsuarioValidarDTO;
import com.example.usuarios.repositorios.UsuarioRep;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRep UsuarioRep;

    public void crearUsuario(UsuarioDTO nuevoUsuarioDTO) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nuevoUsuarioDTO.getNombre());
        nuevoUsuario.setCorreo_electronico(nuevoUsuarioDTO.getCorreo_electronico());
        nuevoUsuario.setDireccion(nuevoUsuarioDTO.getDireccion());
        nuevoUsuario.setContrasena(nuevoUsuarioDTO.getContrasena());
        UsuarioRep.save(nuevoUsuario);
    }

    public void actualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = UsuarioRep.findById(usuarioDTO.getUsuario_id())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioDTO.getUsuario_id()));
        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setCorreo_electronico(usuarioDTO.getCorreo_electronico());
        usuarioExistente.setDireccion(usuarioDTO.getDireccion());
        usuarioExistente.setContrasena(usuarioDTO.getContrasena());
        UsuarioRep.save(usuarioExistente);

    }

    public void eliminarUsuario(UsuarioValidarDTO usuarioValidarDTO) { 
    }


}
