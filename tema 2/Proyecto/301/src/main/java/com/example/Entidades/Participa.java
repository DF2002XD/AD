package com.example.Entidades;

import java.sql.Date;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Entity

public class Participa {
    @EmbeddedId
    ParticipaPK id;

    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "id_evento")
    Evento evento;

    @ManyToOne
    @MapsId("idPersonaje")
    @JoinColumn(name = "id_personaje")
    Personaje personaje;

    Date fecha;
    String rol;

    public Participa() {
    }


    @Override
    public String toString() {
        return "Participa [fecha=" + fecha + ", rol=" + rol + "]";
    }
   

    
}
