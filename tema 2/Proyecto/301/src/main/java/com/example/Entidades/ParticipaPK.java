package com.example.Entidades;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ParticipaPK implements Serializable {

    @Column(name = "id_personaje")
    private int idPersonaje;

    @Column(name = "id_evento")
    private int idEvento;

    public ParticipaPK() {
    }
}
