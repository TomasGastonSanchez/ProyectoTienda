package com.serendipia.proyectoTienda.Servicios;

import Entidades.Usuario;
import Entidades.Usuario;
import com.serendipia.proyectoTienda.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Usuario> obtenerTodos() {
        return userRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return userRepository.findById(id);
    }

    public Usuario crearUser(Usuario user) {
        return userRepository.save(user);
    }

    public Usuario actualizarUser(Long id, Usuario userDetalles) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setUsername(userDetalles.getUsername());
        user.setPassword(userDetalles.getPassword());

        return userRepository.save(user);
    }

    public void eliminarUser(Long id) {
        userRepository.deleteById(id);
    }

}
