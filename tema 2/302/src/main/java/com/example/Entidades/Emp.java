package com.example.Entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "emp")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int id;
    @NonNull
    private String nombre;
    @NonNull
    private String puesto;
    @NonNull
    private double sueldo;
    @NonNull
    private int edad;
    @NonNull
    private String DNI;
    @NonNull
    private boolean esJefe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dpto_id")
    private Dpto dptoElement;

    @Override
    public String toString() {
        return "Emp [id=" + id + ", nombre=" + nombre + ", puesto=" + puesto + ", sueldo=" + sueldo + ", edad=" + edad
                + ", DNI=" + DNI + ", esJefe=" + esJefe + "]";
    }
    

}
