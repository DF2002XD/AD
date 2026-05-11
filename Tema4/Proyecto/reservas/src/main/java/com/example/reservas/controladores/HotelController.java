package com.example.reservas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.reservas.DTO.HotelDTO;
import com.example.reservas.DTO.HotelIDDTO;
import com.example.reservas.DTO.UsuarioValidarDTO;
import com.example.reservas.servicios.HotelService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(nuevoHotelDTO.getNombre());
            usuarioValidarDTO.setContrasena(nuevoHotelDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para crear el hotel");
            }
            HotelService.crearHotel(nuevoHotelDTO);
            return ResponseEntity.ok("Hotel creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el hotel");
        }
    }

    @PatchMapping
    public ResponseEntity<String> actualizarHotel(@RequestBody HotelIDDTO hotelIDDTO) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(hotelIDDTO.getNombre());
            usuarioValidarDTO.setContrasena(hotelIDDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para actualizar el hotel");
            }
            HotelService.actualizarHotel(hotelIDDTO);
            return ResponseEntity.ok("Hotel actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el hotel: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarHotel(@RequestBody HotelIDDTO hotelIDDTO) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(hotelIDDTO.getNombre());
            usuarioValidarDTO.setContrasena(hotelIDDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para eliminar el hotel");
            }
            HotelService.eliminarHotel(hotelIDDTO);
            return ResponseEntity.ok("Hotel eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el hotel: " + e.getMessage());
        }
    }

    @PostMapping("/id/{nombreHotel}")
    public ResponseEntity<String> obtenerIdApartirNombre(@PathVariable String nombreHotel, @RequestBody HotelDTO hotelDTO) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(hotelDTO.getNombre());
            usuarioValidarDTO.setContrasena(hotelDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para obtener el ID del hotel");
            }
            String idHotel = HotelService.obtenerIdApartirNombre(nombreHotel);
            return ResponseEntity.ok("ID del hotel obtenido correctamente: " + idHotel);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener el ID del hotel: " + e.getMessage());
        }
    }

    @PostMapping("/nombre/{id}")
    public ResponseEntity<String> obtenerNombreApartirId(@PathVariable int id, @RequestBody HotelDTO hotelDTO) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(hotelDTO.getNombre());
            usuarioValidarDTO.setContrasena(hotelDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para obtener el nombre del hotel");
            }
            String nombreHotel = HotelService.obtenerNombreApartirId(id);
            return ResponseEntity.ok("Nombre del hotel obtenido correctamente: " + nombreHotel);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener el nombre del hotel: " + e.getMessage());
        }
    }

}