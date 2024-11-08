package com.serendipia.proyectoTienda.Controller;
import Entidades.Usuario;
import com.serendipia.proyectoTienda.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class Login {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody UsuarioLoginRequest request) {
        // Verificar las credenciales y generar el JWT
        Usuario usuario = usuarioService.login(request.getUsername(), request.getPassword());
        System.out.println("Usuario obtenido: " + usuario);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);  // Si las credenciales son incorrectas
    }
    // Clase para recibir la solicitud de login
    public static class UsuarioLoginRequest {
        private String username;
        private String password;
        // Getters y setters
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }
}