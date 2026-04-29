package com.example.reservas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reservas.entidades.Reserva;

public interface ReservaRep extends JpaRepository<Reserva, Integer> {

}
