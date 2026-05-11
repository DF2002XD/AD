package com.example.comentarios.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.comentarios.entidades.Comentario;

public interface ComentarioRep extends MongoRepository<Comentario, String> {

}
