package com.example.Entidades;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "personaje")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String nombre;
    @NonNull
    private String alias;

    @OneToMany(mappedBy = "personaje")
    private List<Participa> listaparticipa;

    @OneToOne()
    @JoinColumn(name = "id_traje", referencedColumnName = "id")
    private Traje id_traje ;

    public void setId_traje(Traje traje) {
        this.id_traje = traje;
        traje.setPersonaje(this);
    }

    @ManyToMany()
    @JoinTable(
        name = "personaje_habilidad",
        joinColumns = @JoinColumn(name = "id_personaje"),
        inverseJoinColumns = @JoinColumn(name = "id_habilidad")
    )
    private List<Habilidad> listahabilidad = new ArrayList<>();
    public void addListahabilidad(Habilidad habilidad) {
        this.listahabilidad.add(habilidad);
        habilidad.addListapersonaje(this);
    }
}
