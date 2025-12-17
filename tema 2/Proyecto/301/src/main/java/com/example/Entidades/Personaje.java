package com.example.Entidades;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
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

    @OneToOne(cascade = CascadeType.ALL)
    private List<Traje> listatraje;
    @JoinColumn(name = "traje_id", referencedColumnName = "id")
    private List<Traje> id_traje = new ArrayList<>();

    public void setListatraje(Traje listatraje) {
        this.listatraje.add(listatraje);
        listatraje.setPersonaje(this);
    }

    @ManyToMany(mappedBy = "listapersonaje")
    @JoinTable(
        name = "personaje_habilidad",
        joinColumns = @JoinColumn(name = "personaje_id"),
        inverseJoinColumns = @JoinColumn(name = "habilidad_id")
    )
    private List<Habilidad> listahabilidad = new ArrayList<>();
    public void addListahabilidad(Habilidad habilidad) {
        this.listahabilidad.add(habilidad);
        habilidad.addListapersonaje(this);
    }
}
