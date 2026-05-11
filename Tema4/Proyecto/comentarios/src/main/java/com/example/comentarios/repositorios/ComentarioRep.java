package com.example.comentarios.repositorios;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.comentarios.entidades.Comentario;

public interface ComentarioRep extends MongoRepository<Comentario, String> {
    
    List<Comentario> findByHotelId(int hotelId);
    
    List<Comentario> findByUsuarioId(int usuarioId);
    
    List<Comentario> findByUsuarioIdAndReservaId(int usuarioId, int reservaId);
    
    
    boolean existsByUsuarioIdAndHotelIdAndReservaId(int usuarioId, int hotelId, int reservaId);
}