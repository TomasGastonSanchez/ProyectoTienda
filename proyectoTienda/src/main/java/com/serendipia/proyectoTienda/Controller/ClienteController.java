package com.serendipia.proyectoTienda.Controller;

import Entidades.Cliente;
import com.serendipia.proyectoTienda.Servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.serendipia.proyectoTienda.Servicios.JWTAuthorizationFilter;

@RestController
@RequestMapping("/api/token-requerido/clientes")
public class ClienteController {
    //GET para obtener información, POST para enviar datos, PUT para actualizar información o DELETE para eliminar datos.
//GET Read data
//PUT Create data
//POST Update data
//DELETE Delete data

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clienteService.obtenerTodos();
    }

    @CrossOrigin(origins = "*",
            methods = {RequestMethod.GET, RequestMethod.POST},
            allowedHeaders = "*",
            allowCredentials = "false")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.obtenerPorId(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    //public Cliente crearCliente(@RequestBody Cliente cliente) {
    //    return clienteService.crearCliente(cliente);
    //}

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteDetalles) {
        Cliente clienteActualizado = clienteService.actualizarCliente(id, clienteDetalles);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }


}
