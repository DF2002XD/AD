package com.example.Entidades;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Participa {
    @Id
    int id;
    @ManyToOne
    Evento evento;
    @ManyToOne
    Personaje personaje;

    Date fecha;
    String rol;
}
