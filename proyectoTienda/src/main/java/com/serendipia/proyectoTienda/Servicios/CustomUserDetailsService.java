package com.serendipia.proyectoTienda.Servicios;

import Entidades.Usuario;
import com.serendipia.proyectoTienda.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Obtener el usuario desde la base de datos
        Usuario user = userRepository.findByUsername(username);

        // Verificar si el usuario existe
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        // Convertir los roles desde la propiedad ROLL (String) a un conjunto de GrantedAuthority
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (user.getROLL() != null && !user.getROLL().isEmpty()) {
            // Dividir la cadena por comas para obtener los roles individuales
            String[] roles = user.getROLL().split(",");
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.trim())); // Agregar el rol como SimpleGrantedAuthority
            }
        }

        // Retornar el UserDetails con roles y otras credenciales
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
