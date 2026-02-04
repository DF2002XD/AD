package com.example.Entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "ciclo")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Ciclo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @NonNull
    private String nombreCiclo;

    @ManyToMany(mappedBy = "ciclo")
    private List<Instituto> instituto = new ArrayList<>();

    public void addListInstituto(Instituto instituto) {
        this.instituto.add(instituto);
    }

    @OneToMany(mappedBy = "ciclo")
    private List<Uso> uso;
}
