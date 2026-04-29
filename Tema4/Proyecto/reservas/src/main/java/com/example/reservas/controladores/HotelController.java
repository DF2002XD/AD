package com.example.reservas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.reservas.DTO.HotelDTO;
import com.example.reservas.DTO.HotelIDDTO;
import com.example.reservas.servicios.HotelService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/reservas/hotel")
public class HotelController {

    @Autowired
    private HotelService HotelService;

    public HotelController(HotelService HotelService) {
        this.HotelService = HotelService;
    }

    @PostMapping
    public ResponseEntity<String> crearHotel(@RequestBody HotelDTO nuevoHotelDTO) {
        try {
            HotelService.crearHotel(nuevoHotelDTO);
            return ResponseEntity.ok("Hotel creado correctamente");   
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el hotel");
        }
    }
    
    @PatchMapping
    public ResponseEntity<String> actualizarHotel(@RequestBody HotelIDDTO hotelIDDTO) {
        try {
            HotelService.actualizarHotel(hotelIDDTO);
            return ResponseEntity.ok("Hotel actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el hotel: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarHotel(@RequestBody HotelIDDTO hotelIDDTO) {
        try {
            HotelService.eliminarHotel(hotelIDDTO);
            return ResponseEntity.ok("Hotel eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el hotel: " + e.getMessage());
        }
    }
}
