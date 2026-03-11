package demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.service.SaludoService;

@RestController
@RequestMapping("/saludo")
public class SaludoController {
    
    @Autowired
    private SaludoService saludoService;

    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<String> saludar(@PathVariable String nombre) {
        String saludo = saludoService.SaludarUsuario(nombre);
        return ResponseEntity.ok(saludo);
    }
        

}
