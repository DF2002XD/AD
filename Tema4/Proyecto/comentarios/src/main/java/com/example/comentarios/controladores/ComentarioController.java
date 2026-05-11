package com.example.comentarios.controladores;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.example.comentarios.servicios.ComentarioService;
import com.example.comentarios.DTO.ComentarioCrearDTO;
import com.example.comentarios.DTO.UsuarioValidarDTO;

@Controller
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @MutationMapping
    public String crearComentario(@Argument ComentarioCrearDTO comentarioCrearDTO) {
        try {
            UsuarioValidarDTO usuarioValidarDTO = new UsuarioValidarDTO();
            usuarioValidarDTO.setNombre(comentarioCrearDTO.getNombre());
            usuarioValidarDTO.setContrasena(comentarioCrearDTO.getContrasena());
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8502/usuarios/validar";
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, usuarioValidarDTO, Boolean.class);
            if (response.getBody() == null || !response.getBody()) {
                return "Usuario no válido para crear el comentario";
            }
            
            return comentarioService.crearComentario(comentarioCrearDTO);
        } catch (Exception e) {
            return "Error al crear el comentario: " + e.getMessage();
        }
    }

}