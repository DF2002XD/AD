package com.example.reservas.servicios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.reservas.DTO.ReservaDTO;
import com.example.reservas.DTO.ReservaEstadoDTO;
import com.example.reservas.entidades.Habitacion;
import com.example.reservas.entidades.Reserva;
import com.example.reservas.repositorios.HabitacionRep;
import com.example.reservas.repositorios.ReservaRep;

@Service
public class ReservaService {

    @Autowired
    private ReservaRep reservaRep;
    @Autowired
    private HabitacionRep habitacionRep;

    public void crearReserva(ReservaDTO nuevaReservaDTO, int usuarioId) {
        Reserva reserva = new Reserva();
        Habitacion habitacion = habitacionRep.findById(nuevaReservaDTO.getHabitacion_id()).orElse(null);
        if (habitacion == null) {
            throw new RuntimeException("Habitación no encontrada con ID: " + nuevaReservaDTO.getHabitacion_id());
        }
        reserva.setUsuario_id(usuarioId);
        reserva.setHabitacion(habitacion);
        reserva.setFecha_inicio(nuevaReservaDTO.getFecha_inicio());
        reserva.setFecha_fin(nuevaReservaDTO.getFecha_fin());
        reserva.setEstado("PENDIENTE");
        reservaRep.save(reserva);
    }


    public void cambiarEstado(ReservaEstadoDTO reservaEstadoDTO) {
        Reserva reserva = reservaRep.findById(reservaEstadoDTO.getReserva_id()).orElse(null);
        if (reserva == null) {
            throw new RuntimeException("Reserva no encontrada con ID: " + reservaEstadoDTO.getReserva_id());
        }
        reserva.setEstado(reservaEstadoDTO.getEstado());
        reservaRep.save(reserva);
    }


    public List<ReservaDTO> listarReservasUsuario(int usuarioId) {
        List<Reserva> reservas = reservaRep.findByUsuario_id(usuarioId);
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva : reservas) {
            ReservaDTO dto = new ReservaDTO();
            dto.setFecha_inicio(reserva.getFecha_inicio());
            dto.setFecha_fin(reserva.getFecha_fin());
            dto.setHabitacion_id(reserva.getHabitacion().getHabitacion_id());
            reservasDTO.add(dto);
        }
        return reservasDTO;
    }


    public List<ReservaDTO> listarReservasSegunEstado(String estado) {
        List<Reserva> reservas = reservaRep.findByEstado(estado);
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva : reservas) {
            ReservaDTO dto = new ReservaDTO();
            dto.setFecha_inicio(reserva.getFecha_inicio());
            dto.setFecha_fin(reserva.getFecha_fin());
            dto.setHabitacion_id(reserva.getHabitacion().getHabitacion_id());
            reservasDTO.add(dto);
        }
        return reservasDTO;
    }

    public boolean checkReserva(int usuarioId, int hotelId, int reservaId) {
        return reservaRep.checkReserva(usuarioId, hotelId, reservaId);
    }

}