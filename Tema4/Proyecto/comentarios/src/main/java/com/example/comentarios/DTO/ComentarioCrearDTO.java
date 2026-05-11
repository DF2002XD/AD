package com.example.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioCrearDTO extends UsuarioValidarDTO {
    private String nombreHotel;
    private int reservaId;
    private double puntuacion;
    private String comentario;

}
