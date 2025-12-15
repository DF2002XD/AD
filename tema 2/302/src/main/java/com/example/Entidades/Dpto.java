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
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "dptos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Dpto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpto_id")
    private int id;

    @NonNull
    private String nombre;
    @NonNull
    private String localidad;


    @OneToMany(mappedBy = "dptoElement", cascade = CascadeType.ALL)
    private List<Emp> emp = new ArrayList<>();
    

    public void addEmp(Emp emp) {
        this.emp.add(emp);
        emp.setDptoElement(this);
    }


    @Override
    public String toString() {
        return "Dpto [id=" + id + ", nombre=" + nombre + ", localidad=" + localidad + "]";
    }
}
