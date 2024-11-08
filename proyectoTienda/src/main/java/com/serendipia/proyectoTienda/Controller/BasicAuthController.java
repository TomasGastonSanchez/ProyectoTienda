package com.serendipia.proyectoTienda.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BasicAuthController {

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @GetMapping("/protected")
        public ResponseEntity<String> protectedEndpoint() {
            return ResponseEntity.ok("Este endpoint requiere roll admin");
        }
}


