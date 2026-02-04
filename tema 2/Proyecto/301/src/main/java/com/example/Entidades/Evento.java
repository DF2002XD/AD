package com.example.Entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "evento")
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Evento {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    private String nombre;
    @NonNull
    private String lugar;

    @OneToMany(mappedBy = "evento")
    private List<Participa> listaparticipa;

    public Evento() {

    }
    @Override
    public String toString() {
        return "Evento [id=" + id + ", nombre=" + nombre + ", lugar=" + lugar + "]";
    }

}
