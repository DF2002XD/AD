package demo.service;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {
    public String SaludarUsuario (String nombre){
        return "Hola " + nombre + "!";
    }
}
