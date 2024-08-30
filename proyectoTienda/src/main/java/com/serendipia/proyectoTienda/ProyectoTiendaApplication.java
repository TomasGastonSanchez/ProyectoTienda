package com.serendipia.proyectoTienda;

import jakarta.websocket.server.ServerEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RestController;


@EntityScan(basePackages = "Entidades")
@SpringBootApplication(scanBasePackages = "com.serendipia.proyectoTienda")
public class ProyectoTiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoTiendaApplication.class,args);
	}

}
////http://26.198.47.254:8082/login.do?jsessionid=e4fcda605db7ddafab790ab371937f39 (link a h2)
//jdbc:h2:tcp://localhost/~/hola
//https://medium.com/eduesqui/como-utilizar-la-base-de-datos-h2-en-spring-boot-db1241d1f7f3
//jdbc:h2:mem:testdb
//http://localhost:8080/api/nombre del get
//{
//    "Id":1,
//    "Nombre":"sa",
//    "Apellido":"sa",
//    "Localidad":"rosario",
//    "Telefono": 123
//    }

