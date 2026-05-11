package com.example.reservas.DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReservaDTO extends UsuarioValidarDTO {
    private int habitacion_id;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    
}
