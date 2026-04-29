package com.example.reservas.DTO;

import java.math.BigDecimal;
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
public class HabitacionDTO {
    private int hotel_id;
    private int numero_habitacion;
    private String tipo;
    private BigDecimal precio;
    private boolean disponible;
}
