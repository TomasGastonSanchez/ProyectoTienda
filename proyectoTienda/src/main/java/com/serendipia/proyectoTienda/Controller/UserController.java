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
@RequestMapping("api/token-requerido/usuarios")
public class UserController {
    //GET para obtener información, POST para enviar datos, PUT para actualizar información o DELETE para eliminar datos.
//GET Read data
//PUT Create data
//POST Update data
//DELETE Delete data

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return userService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> user = userService.obtenerPorId(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    //public Cliente crearCliente(@RequestBody Cliente cliente) {
    //    return clienteService.crearCliente(cliente);
    //}

    @PostMapping
    public Usuario crearUser(@RequestBody Usuario user) {
        return userService.crearUser(user);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUser(@PathVariable Long id, @RequestBody Usuario userDetalles) {
        Usuario userActualizado = userService.actualizarUser(id, userDetalles);
        return ResponseEntity.ok(userActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUser(@PathVariable Long id) {
        userService.eliminarUser(id);
        return ResponseEntity.noContent().build();
    }


}
