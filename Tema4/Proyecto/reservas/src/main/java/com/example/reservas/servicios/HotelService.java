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
        hotel.setNombre(nuevoHotelDTO.getNombreHotel());
        hotel.setDireccion(nuevoHotelDTO.getDireccion());
        HotelRep.save(hotel);
    }

    public void actualizarHotel(HotelIDDTO hotelIDDTO) {
        Hotel hotelExistente = HotelRep.findById(hotelIDDTO.getHotel_id())
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + hotelIDDTO.getHotel_id()));
        hotelExistente.setNombre(hotelIDDTO.getNombreHotel());
        hotelExistente.setDireccion(hotelIDDTO.getDireccion());
        HotelRep.save(hotelExistente);
    }

    public void eliminarHotel(int id) {
        Hotel hotel = HotelRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + id));
        HotelRep.delete(hotel);
    }

    public String obtenerIdApartirNombre(String nombreHotel) {
        Hotel hotel = HotelRep.findByNombre(nombreHotel);
        if (hotel != null) {
            return String.valueOf(hotel.getHotel_id());
        } else {
            throw new RuntimeException("Hotel no encontrado con nombre: " + nombreHotel);
        }
    }

    public String obtenerNombreApartirId(int id) {
        Hotel hotel = HotelRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + id));
        return hotel.getNombre();
    }

}
