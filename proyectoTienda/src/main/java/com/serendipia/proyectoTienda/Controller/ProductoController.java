package com.serendipia.proyectoTienda.Controller;

import Entidades.Producto;
import com.serendipia.proyectoTienda.Servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping()
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardar(producto));
    }



}
