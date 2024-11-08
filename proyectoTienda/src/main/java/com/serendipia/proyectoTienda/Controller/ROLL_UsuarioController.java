package com.serendipia.proyectoTienda.Controller;

import Entidades.Cliente;
import Entidades.Usuario;
import com.serendipia.proyectoTienda.Servicios.ClienteService;
import com.serendipia.proyectoTienda.Servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class ROLL_UsuarioController {

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/hola")
    public String saludos() {
        return "hola";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/tu")
    public String salu() {
        return "hola tu";
    }




}
