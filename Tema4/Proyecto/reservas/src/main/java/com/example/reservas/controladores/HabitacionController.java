package com.example.reservas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.reservas.DTO.HabitacionDTO;
import com.example.reservas.DTO.HabitacionIDDTO;
import com.example.reservas.DTO.UsuarioValidarDTO;
import com.example.reservas.servicios.HabitacionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/reservas/habitacion")
public class HabitacionController {

    @Autowired
    private HabitacionService HabitacionService;

    public HabitacionController(HabitacionService HabitacionService) {
        this.HabitacionService = HabitacionService;
    }

    @PostMapping
    public ResponseEntity<String> crearHabitación(@RequestBody HabitacionDTO nuevaHabitacion) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(nuevaHabitacion.getNombre());
            usuarioValidarDTO.setContrasena(nuevaHabitacion.getContrasena());
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if(response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para crear la habitación");
            }
            HabitacionService.crearHabitacion(nuevaHabitacion);
            return ResponseEntity.ok("Habitación creada correctamente" + response);   
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear la habitación");
        }
    }

    @PatchMapping
    public ResponseEntity<String> actualizarHabitacion(@RequestBody HabitacionIDDTO habitacionIDDTO) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(habitacionIDDTO.getNombre());
            usuarioValidarDTO.setContrasena(habitacionIDDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if(response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para actualizar la habitación");
            }
            HabitacionService.actualizarHabitacion(habitacionIDDTO);
            return ResponseEntity.ok("Habitación actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar la habitación: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable int id, @RequestBody HabitacionIDDTO habitacionIDDTO) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(habitacionIDDTO.getNombre());
            usuarioValidarDTO.setContrasena(habitacionIDDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if(response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para eliminar la habitación");
            }
            HabitacionService.eliminarHabitacion(id);
            return ResponseEntity.ok("Habitación eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la habitación: " + e.getMessage());
        }
    }
}
