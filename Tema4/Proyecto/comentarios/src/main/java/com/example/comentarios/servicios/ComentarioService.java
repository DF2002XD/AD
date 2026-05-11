package com.example.comentarios.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.comentarios.DTO.ComentarioOutputDTO;
import com.example.comentarios.entidades.Comentario;
import com.example.comentarios.repositorios.ComentarioRep;

@Service
public class ComentarioService {

    private final ComentarioRep comentarioRep;

    public ComentarioService(ComentarioRep comentarioRep) {
        this.comentarioRep = comentarioRep;
    }

    public void crearComentario(int usuarioId, int hotelId, int reservaId, 
                                 double puntuacion, String comentario) {
        if (comentarioRep.existsByUsuarioIdAndHotelIdAndReservaId(usuarioId, hotelId, reservaId)) {
            throw new RuntimeException("Ya existe un comentario para esta combinacion");
        }

        Comentario nuevo = Comentario.builder()
            .usuarioId(usuarioId)
            .hotelId(hotelId)
            .reservaId(reservaId)
            .puntuacion(puntuacion)
            .comentario(comentario)
            .fechaCreacion(LocalDateTime.now())
            .build();

        comentarioRep.save(nuevo);
    }

    public String eliminarComentarios() {
        comentarioRep.deleteAll();
        return "Operacion completada correctamente";
    }

    public String eliminarComentarioDeUsuario(int usuarioId, String comentarioId) {
        Comentario comentario = comentarioRep.findById(comentarioId)
            .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        if (comentario.getUsuarioId() != usuarioId) {
            throw new RuntimeException("No tienes permiso para eliminar este comentario");
        }

        comentarioRep.delete(comentario);
        return "Operacion completada correctamente";
    }

    public List<ComentarioOutputDTO> listarComentariosHotel(int hotelId, String nombreHotel) {
        List<Comentario> comentarios = comentarioRep.findByHotelId(hotelId);
        List<ComentarioOutputDTO> resultado = new ArrayList<>();

        for (Comentario c : comentarios) {
            ComentarioOutputDTO dto = ComentarioOutputDTO.builder()
                .nombreHotel(nombreHotel)
                .reservaId(c.getReservaId())
                .puntuacion(c.getPuntuacion())
                .comentario(c.getComentario())
                .build();
            resultado.add(dto);
        }

        return resultado;
    }

    public List<ComentarioOutputDTO> listarComentariosUsuario(int usuarioId) {
        List<Comentario> comentarios = comentarioRep.findByUsuarioId(usuarioId);
        List<ComentarioOutputDTO> resultado = new ArrayList<>();

        for (Comentario c : comentarios) {
            ComentarioOutputDTO dto = ComentarioOutputDTO.builder()
                .nombreHotel("Hotel-" + c.getHotelId())
                .reservaId(c.getReservaId())
                .puntuacion(c.getPuntuacion())
                .comentario(c.getComentario())
                .build();
            resultado.add(dto);
        }

        return resultado;
    }

    public List<ComentarioOutputDTO> mostrarComentarioUsuarioReserva(int usuarioId, int reservaId) {
        List<Comentario> comentarios = comentarioRep.findByUsuarioIdAndReservaId(usuarioId, reservaId);
        List<ComentarioOutputDTO> resultado = new ArrayList<>();

        for (Comentario c : comentarios) {
            ComentarioOutputDTO dto = ComentarioOutputDTO.builder()
                .nombreHotel("Hotel-" + c.getHotelId())
                .reservaId(c.getReservaId())
                .puntuacion(c.getPuntuacion())
                .comentario(c.getComentario())
                .build();
            resultado.add(dto);
        }

        return resultado;
    }

    public double puntuacionMediaHotel(int hotelId) {
        List<Comentario> comentarios = comentarioRep.findByHotelId(hotelId);
        if (comentarios.isEmpty()) {
            return 0.0;
        }

        double suma = 0.0;
        for (Comentario c : comentarios) {
            suma = suma + c.getPuntuacion();
        }

        return suma / comentarios.size();
    }

    public double puntuacionMediaUsuario(int usuarioId) {
        List<Comentario> comentarios = comentarioRep.findByUsuarioId(usuarioId);
        if (comentarios.isEmpty()) {
            return 0.0;
        }

        double suma = 0.0;
        for (Comentario c : comentarios) {
            suma = suma + c.getPuntuacion();
        }

        return suma / comentarios.size();
    }
}