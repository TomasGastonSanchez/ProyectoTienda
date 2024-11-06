package com.serendipia.proyectoTienda.Controller;

import Entidades.Cliente;
import Entidades.Usuario;
import com.serendipia.proyectoTienda.Servicios.ClienteService;
import com.serendipia.proyectoTienda.Servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class ROLL_UsuarioController {

    @GetMapping("/hola")
    public String saludos() {
        return "hola";
    }




}
