package com.example.Entidades;

import java.io.Serializable;
import jakarta.persistence.Column;

public class USoPK implements Serializable {
    @Column(name = "taller_codigo")
    private int tallerCodigo;

    @Column(name = "ciclo_codigo")
    private int cicloCodigo;

    public USoPK() {
    }
}
