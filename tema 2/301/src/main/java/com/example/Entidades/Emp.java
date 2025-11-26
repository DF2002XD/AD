package com.example.Entidades;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "emp")
@Data
@AllArgsConstructor


public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int id;
    private String nombre;
    private String puesto;
    private double sueldo;
    private int edad;
    private String DNI;
    private boolean esJefe;

    public Emp(String string, String string2, int i, int j, String string3, boolean b) {
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dpto_id")
    private Dpto dptoElement;
}
