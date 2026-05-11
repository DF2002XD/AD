package com.example.comentarios.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.example.comentarios.DTO.ComentarioCrearDTO;
import com.example.comentarios.DTO.ComentarioOutputDTO;
import com.example.comentarios.DTO.UsuarioValidarDTO;
import com.example.comentarios.servicios.ComentarioService;

@Controller
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    private static final String URL_USUARIOS = "http://localhost:8502";
    private static final String URL_RESERVAS = "http://localhost:8501";

    // ============================================
    // MUTATIONS
    // ============================================

    @MutationMapping
    public ComentarioCrearDTO crearComentario(@Argument ComentarioCrearDTO comentarioCrearDTO) {
        try {
            // 1. Validar usuario
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(comentarioCrearDTO.getNombre());
            usuarioValidarDTO.setContrasena(comentarioCrearDTO.getContrasena());

            RestTemplate restTemplate = new RestTemplate();
            String urlValidar = URL_USUARIOS + "/usuarios/validar";
            ResponseEntity<Boolean> responseValidar = restTemplate.postForEntity(urlValidar, usuarioValidarDTO,
                    Boolean.class);

            if (responseValidar.getBody() == null || !responseValidar.getBody()) {
                throw new RuntimeException("Usuario no valido para crear el comentario");
            }

            // 2. Obtener ID de usuario
            String urlUsuarioId = URL_USUARIOS + "/usuarios/info/nombre/" + comentarioCrearDTO.getNombre();
            ResponseEntity<String> responseUsuarioId = restTemplate.getForEntity(urlUsuarioId, String.class);
            int usuarioId = Integer.parseInt(responseUsuarioId.getBody());

            // 3. Obtener ID de hotel (POST con credenciales en body)
            String urlHotelId = URL_RESERVAS + "/reservas/hotel/id/" + comentarioCrearDTO.getNombreHotel();
            ResponseEntity<String> responseHotelId = restTemplate.postForEntity(urlHotelId, usuarioValidarDTO,
                    String.class);
            int hotelId = Integer.parseInt(responseHotelId.getBody());

            // 4. Verificar reserva
            String urlCheck = URL_RESERVAS + "/reservas/check/" + usuarioId + "/" + hotelId + "/"
                    + comentarioCrearDTO.getReservaId();
            ResponseEntity<Boolean> responseCheck = restTemplate.getForEntity(urlCheck, Boolean.class);

            if (responseCheck.getBody() == null || !responseCheck.getBody()) {
                throw new RuntimeException("La reserva no existe para este usuario y hotel");
            }

            // 5. Guardar
            comentarioService.crearComentario(
                    usuarioId,
                    hotelId,
                    comentarioCrearDTO.getReservaId(),
                    comentarioCrearDTO.getPuntuacion(),
                    comentarioCrearDTO.getComentario());

            return comentarioCrearDTO;

        } catch (Exception e) {
            throw new RuntimeException("Error al crear el comentario: " + e.getMessage());
        }
    }

    @MutationMapping
    public String eliminarComentarios() {
        try {
            return comentarioService.eliminarComentarios();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar los comentarios: " + e.getMessage());
        }
    }

    @MutationMapping
    public String eliminarComentarioDeUsuario(
            @Argument String nombre,
            @Argument String contrasena,
            @Argument String comentarioId) {

        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(nombre);
            usuarioValidarDTO.setContrasena(contrasena);

            RestTemplate restTemplate = new RestTemplate();
            String urlValidar = URL_USUARIOS + "/usuarios/validar";
            ResponseEntity<Boolean> responseValidar = restTemplate.postForEntity(urlValidar, usuarioValidarDTO,
                    Boolean.class);

            if (responseValidar.getBody() == null || !responseValidar.getBody()) {
                throw new RuntimeException("Usuario no valido");
            }

            String urlUsuarioId = URL_USUARIOS + "/usuarios/info/nombre/" + nombre;
            ResponseEntity<String> responseUsuarioId = restTemplate.getForEntity(urlUsuarioId, String.class);
            int usuarioId = Integer.parseInt(responseUsuarioId.getBody());

            return comentarioService.eliminarComentarioDeUsuario(usuarioId, comentarioId);

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el comentario: " + e.getMessage());
        }
    }

    // ============================================
    // QUERIES
    // ============================================

    @QueryMapping
    public List<ComentarioOutputDTO> listarComentariosHotel(
            @Argument String nombre,
            @Argument String contrasena,
            @Argument String nombreHotel) {

        try {
            UsuarioValidarDTO credenciales = new UsuarioValidarDTO();
            credenciales.setNombre(nombre);
            credenciales.setContrasena(contrasena);

            RestTemplate restTemplate = new RestTemplate();
            String urlHotelId = URL_RESERVAS + "/reservas/hotel/id/" + nombreHotel;
            ResponseEntity<String> responseHotelId = restTemplate.postForEntity(urlHotelId, credenciales, String.class);

            String bodyHotelId = responseHotelId.getBody();
            int hotelId = Integer.parseInt(bodyHotelId != null ? bodyHotelId.replaceAll("[^0-9]", "") : "0");

            return comentarioService.listarComentariosHotel(hotelId, nombreHotel);

        } catch (Exception e) {
            throw new RuntimeException("Error al listar comentarios del hotel: " + e.getMessage());
        }
    }

    @QueryMapping
    public List<ComentarioOutputDTO> listarComentariosUsuario(
            @Argument String nombre,
            @Argument String contrasena) {

        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(nombre);
            usuarioValidarDTO.setContrasena(contrasena);

            RestTemplate restTemplate = new RestTemplate();
            String urlValidar = URL_USUARIOS + "/usuarios/validar";
            ResponseEntity<Boolean> responseValidar = restTemplate.postForEntity(urlValidar, usuarioValidarDTO,
                    Boolean.class);

            if (responseValidar.getBody() == null || !responseValidar.getBody()) {
                throw new RuntimeException("Usuario no valido");
            }

            String urlUsuarioId = URL_USUARIOS + "/usuarios/info/nombre/" + nombre;
            ResponseEntity<String> responseUsuarioId = restTemplate.getForEntity(urlUsuarioId, String.class);
            int usuarioId = Integer.parseInt(responseUsuarioId.getBody());

            return comentarioService.listarComentariosUsuario(usuarioId, nombre, contrasena);

        } catch (Exception e) {
            throw new RuntimeException("Error al listar comentarios del usuario: " + e.getMessage());
        }
    }

    @QueryMapping
    public List<ComentarioOutputDTO> mostrarComentarioUsuarioReserva(
            @Argument String nombre,
            @Argument String contrasena,
            @Argument int reservaId) {

        try {
            // 1. Validar credenciales
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(nombre);
            usuarioValidarDTO.setContrasena(contrasena);

            RestTemplate restTemplate = new RestTemplate();
            String urlValidar = URL_USUARIOS + "/usuarios/validar";
            ResponseEntity<Boolean> responseValidar = restTemplate.postForEntity(
                    urlValidar, usuarioValidarDTO, Boolean.class);

            if (responseValidar.getBody() == null || !responseValidar.getBody()) {
                throw new RuntimeException("Usuario no valido");
            }

            // 2. Obtener ID del usuario
            String urlUsuarioId = URL_USUARIOS + "/usuarios/info/nombre/" + nombre;
            ResponseEntity<String> responseUsuarioId = restTemplate.getForEntity(urlUsuarioId, String.class);
            int usuarioId = Integer.parseInt(responseUsuarioId.getBody());

            // 3. Llamar al service con usuarioId + reservaId
            return comentarioService.mostrarComentarioUsuarioReserva(usuarioId, reservaId, nombre, contrasena);

        } catch (Exception e) {
            throw new RuntimeException("Error al mostrar comentario: " + e.getMessage());
        }
    }

    @QueryMapping
    public double puntuacionMediaHotel(
            @Argument String nombre,
            @Argument String contrasena,
            @Argument String nombreHotel) {

        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(nombre);
            usuarioValidarDTO.setContrasena(contrasena);

            RestTemplate restTemplate = new RestTemplate();
            String urlValidar = URL_USUARIOS + "/usuarios/validar";
            ResponseEntity<Boolean> responseValidar = restTemplate.postForEntity(
                    urlValidar, usuarioValidarDTO, Boolean.class);

            if (responseValidar.getBody() == null || !responseValidar.getBody()) {
                throw new RuntimeException("Usuario no valido");
            }

            // OBTENER ID DEL HOTEL (con limpieza del texto de respuesta)
            String urlHotelId = URL_RESERVAS + "/reservas/hotel/id/" + nombreHotel;
            ResponseEntity<String> responseHotelId = restTemplate.postForEntity(
                    urlHotelId, usuarioValidarDTO, String.class);

            String bodyHotelId = responseHotelId.getBody();
            int hotelId = Integer.parseInt(
                    bodyHotelId != null ? bodyHotelId.replaceAll("[^0-9]", "") : "0");

            return comentarioService.puntuacionMediaHotel(hotelId);

        } catch (Exception e) {
            throw new RuntimeException("Error al calcular puntuacion media del hotel: " + e.getMessage());
        }
    }

    @QueryMapping
    public double puntuacionMediaUsuario(
            @Argument String nombre,
            @Argument String contrasena) {

        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(nombre);
            usuarioValidarDTO.setContrasena(contrasena);

            RestTemplate restTemplate = new RestTemplate();
            String urlValidar = URL_USUARIOS + "/usuarios/validar";
            ResponseEntity<Boolean> responseValidar = restTemplate.postForEntity(urlValidar, usuarioValidarDTO,
                    Boolean.class);

            if (responseValidar.getBody() == null || !responseValidar.getBody()) {
                throw new RuntimeException("Usuario no valido");
            }

            String urlUsuarioId = URL_USUARIOS + "/usuarios/info/nombre/" + nombre;
            ResponseEntity<String> responseUsuarioId = restTemplate.getForEntity(urlUsuarioId, String.class);
            int usuarioId = Integer.parseInt(responseUsuarioId.getBody());

            return comentarioService.puntuacionMediaUsuario(usuarioId);

        } catch (Exception e) {
            throw new RuntimeException("Error al calcular puntuacion media del usuario: " + e.getMessage());
        }
    }
}