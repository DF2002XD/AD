package com.example.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "traje")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Traje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String especificacion;

    @OneToOne(mappedBy = "id_traje")
    private Personaje personaje;

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}