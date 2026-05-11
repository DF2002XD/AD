package com.example.reservas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.reservas.entidades.Reserva;
import java.util.List;

@Repository
public interface ReservaRep extends JpaRepository<Reserva, Integer> {

    @Query("SELECT r FROM Reserva r WHERE r.usuario_id = :usuarioId")
    List<Reserva> findByUsuario_id(@Param("usuarioId") int usuarioId);

    @Query("SELECT r FROM Reserva r WHERE r.estado = :estado")
    List<Reserva> findByEstado(@Param("estado") String estado);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reserva r WHERE r.reserva_id = :reservaId AND r.usuario_id = :usuarioId AND r.habitacion.hotel.hotel_id = :hotelId")
    boolean checkReserva(@Param("usuarioId") int usuarioId, @Param("hotelId") int hotelId, @Param("reservaId") int reservaId);
}
