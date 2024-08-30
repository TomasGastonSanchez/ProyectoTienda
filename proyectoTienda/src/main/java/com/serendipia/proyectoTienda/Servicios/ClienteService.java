package com.serendipia.proyectoTienda.Servicios;

import Entidades.Cliente;
import com.serendipia.proyectoTienda.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteDetalles) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.SetNombre(clienteDetalles.GetNombre());
        cliente.SetApellido(clienteDetalles.GetApellido());
        cliente.SetLocalidad(clienteDetalles.GetLocalidad());
        cliente.SetTelefono(clienteDetalles.GetTelefono());

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }



}

