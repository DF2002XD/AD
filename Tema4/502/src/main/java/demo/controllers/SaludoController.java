package demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import demo.Entitys.Info;
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
        
    @GetMapping("/{nombrei}-{idioma}")
    public ResponseEntity<String> saludar(@PathVariable String nombrei, @PathVariable String idioma) {
        String saludo = saludoService.SaludarUsuarioIdioma(nombrei, idioma);
        return ResponseEntity.ok(saludo);
    }

    @PostMapping("/cuerpo")
    public ResponseEntity<String> saludar(@RequestBody Info info ) {
        String saludo = saludoService.SaludarUsuarioIdioma(info.getNombre(), info.getIdioma());
        return ResponseEntity.ok(saludo);
    }
    
}
