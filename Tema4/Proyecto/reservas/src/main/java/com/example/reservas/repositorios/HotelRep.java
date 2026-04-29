package com.example.reservas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reservas.entidades.Hotel;

public interface HotelRep extends JpaRepository<Hotel, Integer> {

}
