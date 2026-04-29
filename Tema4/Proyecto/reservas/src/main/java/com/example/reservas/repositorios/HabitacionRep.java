package com.example.reservas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reservas.entidades.Habitacion;

public interface HabitacionRep extends JpaRepository<Habitacion, Integer> {

}
