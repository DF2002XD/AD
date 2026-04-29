package com.example.reservas.entidades;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "habitacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int habitacion_id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @NonNull
    private int numero_habitacion;
    @NonNull
    private String tipo;
    @NonNull
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;
    @NonNull
    private boolean disponible;

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL)
    private List<Reserva> reservas;
}
