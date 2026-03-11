package demo.service;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {
    public String SaludarUsuario(String nombre) {
        return "Hola " + nombre + "!";
    }

    public String SaludarUsuarioIdioma(String nombre, String idioma) {
        switch (idioma) {
            case "ESPAÑOL":
                return "Hola " + nombre + "!";
            case "INGLES":
                return "Hello " + nombre + "!";
            case "FRANCES":
                return "Bonjour " + nombre + "!";
            case "ALEMAN":
                return "Hallo " + nombre + "!";
            case "ITALIANO":
                return "Ciao " + nombre + "!";
            default:
                return "Saludo no soportado para el idioma especificado";
        }
    }

}
