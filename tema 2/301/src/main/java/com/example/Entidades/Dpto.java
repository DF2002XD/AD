package com.example.Entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "dptos")
@Data
@AllArgsConstructor
public class Dpto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpto_id")
    private int id;
    private String nombre;
    private String localidad;

    public Dpto() {
    }

    @OneToMany(mappedBy = "dptoElement", cascade = CascadeType.ALL)
    private List<Emp> emp = new ArrayList<>();
    

    public void addEmp(Emp emp) {
        this.emp.add(emp);
    }
}
