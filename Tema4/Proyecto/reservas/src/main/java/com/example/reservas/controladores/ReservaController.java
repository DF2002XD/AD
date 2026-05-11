package com.example.reservas.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.reservas.DTO.ReservaDTO;
import com.example.reservas.DTO.ReservaEstadoDTO;
import com.example.reservas.DTO.UsuarioValidarDTO;
import com.example.reservas.servicios.ReservaService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDTO nuevaReservaDTO) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(nuevaReservaDTO.getNombre());
            usuarioValidarDTO.setContrasena(nuevaReservaDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para crear la reserva");
            }

            String urlUsuarioID = "http://localhost:8502/usuarios/info/nombre/" + nuevaReservaDTO.getNombre();
            ResponseEntity<String> responseUsuario = restTemplate.getForEntity(urlUsuarioID, String.class);
            int usuarioId = Integer.parseInt(responseUsuario.getBody());

            reservaService.crearReserva(nuevaReservaDTO, usuarioId);
            return ResponseEntity.ok("Reserva creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear la reserva: " + e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<String> cambiarEstado(@RequestBody ReservaEstadoDTO reservaEstadoDTO) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(reservaEstadoDTO.getNombre());
            usuarioValidarDTO.setContrasena(reservaEstadoDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para crear la reserva");
            }

            reservaService.cambiarEstado(reservaEstadoDTO);
            return ResponseEntity.ok("Estado de la reserva cambiado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al cambiar el estado de la reserva: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarReservasUsuario(@RequestBody UsuarioValidarDTO usuarioValidarDTO) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para listar las reservas");
            }

            String urlUsuarioID = "http://localhost:8502/usuarios/info/nombre/" + usuarioValidarDTO.getNombre();
            ResponseEntity<String> responseUsuario = restTemplate.getForEntity(urlUsuarioID, String.class);
            int usuarioId = Integer.parseInt(responseUsuario.getBody());

            List<ReservaDTO> reservas = reservaService.listarReservasUsuario(usuarioId);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al listar las reservas: " + e.getMessage());
        }
    }
    @GetMapping("/{estado}")
    public ResponseEntity<?> listarReservasSegunEstado(@PathVariable String estado, @RequestBody UsuarioValidarDTO usuarioValidarDTO) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return ResponseEntity.status(401).body("Usuario no válido para crear la reserva");
            }
            List<ReservaDTO> reservas = reservaService.listarReservasSegunEstado(estado);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al listar las reservas: " + e.getMessage());
        }
    }

    @GetMapping("/check/{idUsuario}/{idHotel}/{idReserva}")
    public ResponseEntity<Boolean> checkReserva(@PathVariable int idUsuario, @PathVariable int idHotel, @PathVariable int idReserva) {
        try {
            boolean existe = reservaService.checkReserva(idUsuario, idHotel, idReserva);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }

}