package com.example.reservas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.reservas.DTO.HotelDTO;
import com.example.reservas.DTO.HotelIDDTO;
import com.example.reservas.entidades.Hotel;
import com.example.reservas.repositorios.HotelRep;

@Service
public class HotelService {

    @Autowired
    private HotelRep HotelRep;

    public void crearHotel(HotelDTO nuevoHotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setNombre(nuevoHotelDTO.getNombre());
        hotel.setDireccion(nuevoHotelDTO.getDireccion());
        HotelRep.save(hotel);
    }

    public void actualizarHotel(HotelIDDTO hotelIDDTO) {
        Hotel hotelExistente = HotelRep.findById(hotelIDDTO.getHotel_id())
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + hotelIDDTO.getHotel_id()));
        hotelExistente.setNombre(hotelIDDTO.getNombre());
        hotelExistente.setDireccion(hotelIDDTO.getDireccion());
        HotelRep.save(hotelExistente);
    }

    public void eliminarHotel(HotelIDDTO hotelIDDTO) {
        Hotel hotel = HotelRep.findById(hotelIDDTO.getHotel_id())
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + hotelIDDTO.getHotel_id()));
        HotelRep.delete(hotel);
    }

}
