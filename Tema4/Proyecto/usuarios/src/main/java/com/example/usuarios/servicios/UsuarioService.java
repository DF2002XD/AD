package com.example.usuarios.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.usuarios.entidades.Usuario;
import com.example.usuarios.entidades.UsuarioDTO;
import com.example.usuarios.entidades.UsuarioIDDTO;
import com.example.usuarios.entidades.UsuarioValidarDTO;
import com.example.usuarios.repositorios.UsuarioRep;

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

    public void actualizarUsuario(UsuarioIDDTO usuarioIDDTO) {
        Usuario usuarioExistente = UsuarioRep.findById(usuarioIDDTO.getUsuario_id())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioIDDTO.getUsuario_id()));
        usuarioExistente.setNombre(usuarioIDDTO.getNombre());
        usuarioExistente.setCorreo_electronico(usuarioIDDTO.getCorreo_electronico());
        usuarioExistente.setDireccion(usuarioIDDTO.getDireccion());
        usuarioExistente.setContrasena(usuarioIDDTO.getContrasena());
        UsuarioRep.save(usuarioExistente);
    }

    public void eliminarUsuario(UsuarioValidarDTO usuarioValidarDTO) {
        Usuario usuario = UsuarioRep.findByNombreAndContrasena(
            usuarioValidarDTO.getNombre(), 
            usuarioValidarDTO.getContrasena());
        if (usuario != null) {
            UsuarioRep.delete(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado con nombre: " + usuarioValidarDTO.getNombre());
        }
    }

    public void validarUsuario(UsuarioValidarDTO usuarioValidarDTO) {
        Usuario usuario = UsuarioRep.findByNombreAndContrasena(
            usuarioValidarDTO.getNombre(), 
            usuarioValidarDTO.getContrasena());
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con nombre: " + usuarioValidarDTO.getNombre());
        }
    }

    public String obtenerInfoUsuarioPorId(int id) {
        Usuario usuario = UsuarioRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return usuario.getNombre();
    }

    public String obtenerInfoUsuarioPorNombre(String nombre) {
        Usuario usuario = UsuarioRep.findByNombre(nombre);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con nombre: " + nombre);
        }
        return String.valueOf(usuario.getUsuario_id());
    }

    public void checkIfExist(int id) {
        UsuarioRep.findById(id);
        if (UsuarioRep.findById(id).isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

}
