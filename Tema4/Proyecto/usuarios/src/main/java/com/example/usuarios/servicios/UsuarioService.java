package com.example.usuarios.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.usuarios.entidades.Usuario;
import com.example.usuarios.repositorios.UsuarioRep;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRep UsuarioRep;

    public Usuario crearUsuario(Usuario nuevoUsuario) {
        return UsuarioRep.save(nuevoUsuario);
    }

}
