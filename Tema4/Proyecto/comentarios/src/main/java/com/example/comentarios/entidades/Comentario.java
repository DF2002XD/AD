package com.example.comentarios.entidades;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "comentarios")
public class Comentario {
    @Id
    private String _id;

    private int usuario_id;
    private int hotel_id;
    private int reserva_id;
    private BigDecimal puntuacion;
    private String comentario;
    private String fechaCreacion;
}
