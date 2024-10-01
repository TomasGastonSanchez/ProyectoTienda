package com.serendipia.proyectoTienda.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Indica que manejará las solicitudes HTTP
@RequestMapping("/api") //Indica que es una anotación más general que se puede usar para
// manejar diferentes tipos de solicitudes HTTP como GET, POST, PUT, DELETE
public class EndpointController {

    @GetMapping("/saludo") //Se utiliza para manejar solicitudes HTTP GET.
    public String Saludo(){
        return "Hola";
    }

}
