package com.example.Entidades;

import java.sql.Date;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

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
    @Override
    public String toString() {
        return "Participa [fecha=" + fecha + ", rol=" + rol + "]";
    }
   

    
}
