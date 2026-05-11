package com.example.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioOutputDTO {
    private String nombreHotel;
    private int reservaId;
    private double puntuacion;
    private String comentario;
}