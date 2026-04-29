package com.example.reservas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.reservas.DTO.HabitacionDTO;
import com.example.reservas.DTO.HabitacionIDDTO;
import com.example.reservas.entidades.Habitacion;
import com.example.reservas.entidades.Hotel;
import com.example.reservas.repositorios.HabitacionRep;
import com.example.reservas.repositorios.HotelRep;

@Service
public class HabitacionService {
    @Autowired
    private HabitacionRep HabitacionRep;
    @Autowired
    private HotelRep HotelRep;

    public void crearHabitación(HabitacionDTO nuevaHabitaciónDto) {
        Hotel hotel = HotelRep.findById(nuevaHabitaciónDto.getHotel_id())
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + nuevaHabitaciónDto.getHotel_id()));
        Habitacion nuevaHabitacion = new Habitacion();
        nuevaHabitacion.setHotel(hotel);
        nuevaHabitacion.setNumero_habitacion(nuevaHabitaciónDto.getNumero_habitacion());
        nuevaHabitacion.setTipo(nuevaHabitaciónDto.getTipo());
        nuevaHabitacion.setPrecio(nuevaHabitaciónDto.getPrecio());
        nuevaHabitacion.setDisponible(nuevaHabitaciónDto.isDisponible());
        HabitacionRep.save(nuevaHabitacion);
    }

    public void actualizarHabitacion(HabitacionIDDTO habitacionIDDTO) {
        Habitacion habitacionExistente = HabitacionRep.findById(habitacionIDDTO.getHabitacion_id())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + habitacionIDDTO.getHabitacion_id()));
        Hotel hotel = HotelRep.findById(habitacionIDDTO.getHotel_id())
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + habitacionIDDTO.getHotel_id()));
        habitacionExistente.setHotel(hotel);
        habitacionExistente.setNumero_habitacion(habitacionIDDTO.getNumero_habitacion());
        habitacionExistente.setTipo(habitacionIDDTO.getTipo());
        habitacionExistente.setPrecio(habitacionIDDTO.getPrecio());
        habitacionExistente.setDisponible(habitacionIDDTO.isDisponible());
        HabitacionRep.save(habitacionExistente);
    }

    public void eliminarHabitacion(HabitacionIDDTO habitacionIDDTO) {
        Habitacion habitacion = HabitacionRep.findById(habitacionIDDTO.getHabitacion_id())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + habitacionIDDTO.getHabitacion_id()));
        HabitacionRep.delete(habitacion);
    }
    
}
