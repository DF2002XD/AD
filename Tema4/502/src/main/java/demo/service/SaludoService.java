package demo.service;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {
    public String SaludarUsuario(String nombre) {
        return "Hola " + nombre + "!";
    }

    public String SaludarUsuarioIdioma(String nombrei, String idioma) {
        switch (idioma) {
            case "ESPAÑOL":
                return "Hola " + nombrei + "!";
            case "INGLES":
                return "Hello " + nombrei + "!";
            case "FRANCES":
                return "Bonjour " + nombrei + "!";
            case "ALEMAN":
                return "Hallo " + nombrei + "!";
            case "ITALIANO":
                return "Ciao " + nombrei + "!";
            default:
                return "Saludo no soportado para el idioma especificado";
        }
    }

}
