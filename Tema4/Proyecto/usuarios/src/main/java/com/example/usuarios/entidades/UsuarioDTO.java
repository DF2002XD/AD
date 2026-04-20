package com.example.usuarios.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UsuarioDTO {
    private int usuario_id;
    private String nombre;
    private String correo_electronico;
    private String direccion;
    private String contrasena;
    public UsuarioDTO(Usuario usuario) {
        this.usuario_id = usuario.getUsuario_id();
        this.nombre = usuario.getNombre();
        this.correo_electronico = usuario.getCorreo_electronico();
        this.direccion = usuario.getDireccion();
        this.contrasena = usuario.getContrasena();
    }

}
