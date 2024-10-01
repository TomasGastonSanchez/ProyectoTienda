package com.serendipia.proyectoTienda.Servicios;

import Entidades.Vendedor;
import com.serendipia.proyectoTienda.Repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public Vendedor guardar(Vendedor vendedor) {
        return vendedor;
    }

    public List<Vendedor> obtenerTodos() {
        return vendedorRepository.findAll();
    }

    public Optional<Vendedor> VendedorobtenerPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    public Vendedor crearVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Vendedor actualizarCliente(Long id, Vendedor vendedorDetalles) {
        Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        vendedor.SetNombre(vendedorDetalles.GetNombre());
        vendedor.SetApellido(vendedorDetalles.GetApellido());
        vendedor.SetLocalidad(vendedorDetalles.GetLocalidad());
        vendedor.SetTelefono(vendedorDetalles.GetTelefono());

        return vendedorRepository.save(vendedor);
    }

    public void eliminarVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }


}
