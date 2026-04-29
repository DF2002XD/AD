package com.example.reservas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.reservas.DTO.HabitacionDTO;
import com.example.reservas.DTO.HabitacionIDDTO;
import com.example.reservas.servicios.HabitacionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/reservas/habitacion")
public class HabitacionController {

    @Autowired
    private HabitacionService HabitacionService;

    public HabitacionController(HabitacionService HabitacionService) {
        this.HabitacionService = HabitacionService;
    }

    @PostMapping
    public ResponseEntity<String> crearHabitación(@RequestBody HabitacionDTO nuevaHabitación) {
        try {
            HabitacionService.crearHabitación(nuevaHabitación);
            return ResponseEntity.ok("Habitación creada correctamente");   
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear la habitación");
        }
    }

    @PatchMapping
    public ResponseEntity<String> actualizarHabitacion(@RequestBody HabitacionIDDTO habitacionIDDTO) {
        try {
            HabitacionService.actualizarHabitacion(habitacionIDDTO);
            return ResponseEntity.ok("Habitación actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar la habitación: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarHabitacion(@RequestBody HabitacionIDDTO habitacionIDDTO) {
        try {
            HabitacionService.eliminarHabitacion(habitacionIDDTO);
            return ResponseEntity.ok("Habitación eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la habitación: " + e.getMessage());
        }
    }
}
