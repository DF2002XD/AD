package com.example.Entidades;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.google.protobuf.Generated;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "habilidad")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String nombre;
    @NonNull
    private String descripcion;

    @ManyToMany(mappedBy = "listahabilidad")
    private List<Personaje> listapersonaje = new ArrayList<>();

    public void addListapersonaje(Personaje personaje) {
        this.listapersonaje.add(personaje);
    }
}