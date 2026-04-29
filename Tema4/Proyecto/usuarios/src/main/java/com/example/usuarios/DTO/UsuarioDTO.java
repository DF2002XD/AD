package com.example.usuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UsuarioDTO {
    private String nombre;
    private String correo_electronico;
    private String direccion;
    private String contrasena;
}
