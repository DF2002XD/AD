package com.example.Entidades;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Uso {
 @EmbeddedId
    USoPK id;

    @ManyToOne
    @MapsId("tallerCodigo")
    @JoinColumn(name = "taller_codigo")
    Taller taller;

    @ManyToOne
    @MapsId("cicloCodigo")
    @JoinColumn(name = "ciclo_codigo")
    Ciclo ciclo;

    Date fecha;
    Time hora;
    public Uso() {
    }
    

}
