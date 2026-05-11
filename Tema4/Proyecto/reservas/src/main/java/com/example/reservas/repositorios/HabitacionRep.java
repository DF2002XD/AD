package com.example.reservas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.reservas.entidades.Habitacion;

@Repository
public interface HabitacionRep extends JpaRepository<Habitacion, Integer> {

}
