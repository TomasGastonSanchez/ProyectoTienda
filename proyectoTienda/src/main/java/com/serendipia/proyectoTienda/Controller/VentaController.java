package com.serendipia.proyectoTienda.Controller;

import Entidades.Venta;
import com.serendipia.proyectoTienda.Servicios.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/venta")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping()
    public ResponseEntity<Venta> registrarVenta(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaService.guardar(venta));
    }






}
