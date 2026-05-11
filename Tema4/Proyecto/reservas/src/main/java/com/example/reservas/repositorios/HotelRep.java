package com.example.reservas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.reservas.entidades.Hotel;

@Repository
public interface HotelRep extends JpaRepository<Hotel, Integer> {
    Hotel findByNombre(String nombreHotel);
}
