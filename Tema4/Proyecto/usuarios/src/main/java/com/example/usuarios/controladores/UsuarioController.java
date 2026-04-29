package com.example.usuarios.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.usuarios.entidades.UsuarioDTO;
import com.example.usuarios.entidades.UsuarioIDDTO;
import com.example.usuarios.entidades.UsuarioValidarDTO;
import com.example.usuarios.servicios.UsuarioService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService UsuarioService;

    public UsuarioController(UsuarioService UsuarioService) {
        this.UsuarioService = UsuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioDTO nuevoUsuario) {
        try {
            UsuarioService.crearUsuario(nuevoUsuario);
            return ResponseEntity.ok("Usuario creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el usuario: " + e.getMessage());
        }
    }

    @PutMapping("/registrar")
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioIDDTO usuarioIDDTO) {
        try {
            UsuarioService.actualizarUsuario(usuarioIDDTO);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el usuario: " + e.getMessage());
        }

    }

    @DeleteMapping
    public ResponseEntity<String> eliminarUsuario(@RequestBody UsuarioValidarDTO usuarioValidarDTO) {
        try {
            UsuarioService.eliminarUsuario(usuarioValidarDTO);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el usuario: " + e.getMessage());
        }
        
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestBody UsuarioValidarDTO usuarioValidarDTO) {
        try {
            UsuarioService.validarUsuario(usuarioValidarDTO);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }
    
    @GetMapping("/info/id/{id}")
    public ResponseEntity<String> obtenerInfoUsuarioPorId(@PathVariable int id) {
        try {
            String nombre = UsuarioService.obtenerInfoUsuarioPorId(id);
            return ResponseEntity.ok(nombre);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener info del usuario: " + e.getMessage());
        }
    }

    @GetMapping("/info/nombre/{nombre}")
    public ResponseEntity<String> obtenerInfoUsuarioPorNombre(@PathVariable String nombre) {
        try {
            String idUsuario = UsuarioService.obtenerInfoUsuarioPorNombre(nombre);
            return ResponseEntity.ok(idUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener info del usuario: " + e.getMessage());
        }
    }
    
    @GetMapping("/checkIfExist/{id}")
    public ResponseEntity<Boolean> checkIfExist(@PathVariable int id)  {
        try {
            UsuarioService.checkIfExist(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(false);
        }
    }
    
}
