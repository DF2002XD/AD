package com.example.usuarios.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioValidarDTO {
    private String nombre;
    private String contrasena;

    public UsuarioValidarDTO(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.contrasena = usuario.getContrasena();
    }
}
