package com.example.Entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "director")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codigoCuerpo;
    private int edad;
    private String nombre;

    @OneToOne(mappedBy = "director")
    private Instituto instituto;

    public void setId_Director(Instituto instituto) {
        this.instituto = instituto;
        if (instituto != null) {
            instituto.setId_Instituto(this);
        }
        
    }

    @Override
    public String toString() {
        return "Director [id=" + id + ", codigoCuerpo=" + codigoCuerpo + ", edad=" + edad + ", nombre=" + nombre + "]";
    }

}
