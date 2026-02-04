package com.example.Entidades;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "instituto")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Instituto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @NonNull
    private String nombre;
    @NonNull
    @Column(columnDefinition = "CHAR(9)")
    private String tf;

    @ManyToMany()
    @JoinTable(name = "ies_ciclos", joinColumns = @JoinColumn(name = "cod_instituto", referencedColumnName = "codigo"), inverseJoinColumns = @JoinColumn(name = "cod_ciclo", referencedColumnName = "codigo"))
    private List<Ciclo> ciclo = new ArrayList<>();

    public void addListCiclo(Ciclo ciclo) {
        this.ciclo.add(ciclo);
        ciclo.addListInstituto(this);
    }

    @OneToOne()
    @JoinColumn(name = "director", referencedColumnName = "id")
    private Director director;

    public void setId_Instituto(Director director) {
        this.director = director;
        if (director != null) {
            director.setId_Director(this);
        }

    }

    public Instituto(){}

    @Override
    public String toString() {
        return "Instituto [codigo=" + codigo + ", nombre=" + nombre + ", tf=" + tf + ", director=" + director + "]";
    }
    
}
