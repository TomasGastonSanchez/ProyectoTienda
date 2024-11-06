package com.serendipia.proyectoTienda.Controller;

import Entidades.Vendedor;
import com.serendipia.proyectoTienda.Servicios.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/vendedor")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public List<Vendedor> obtenerTodos() {
        return vendedorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> obtenerPorId(@PathVariable Long id) {
        Optional<Vendedor> vendedor = vendedorService.VendedorobtenerPorId(id);

        if (vendedor.isPresent()) {
            return ResponseEntity.ok(vendedor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Vendedor> registrarVendedor(@RequestBody Vendedor vendedor) {
        return ResponseEntity.ok(vendedorService.guardar(vendedor));
    }




}
