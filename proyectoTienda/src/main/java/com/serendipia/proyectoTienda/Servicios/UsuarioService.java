package com.serendipia.proyectoTienda.Servicios;

import Entidades.Usuario;
import com.serendipia.proyectoTienda.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UserRepository usuarioRepository;  // Repositorio para interactuar con la tabla usuarios

    private static final String SECRET_KEY = JWTConfig.SECRET_KEY;


    public Usuario login(String username, String password) {
        // Verificar las credenciales del usuario (esto es solo un ejemplo)
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario != null && usuario.getPassword().equals(password)) {
            // Generar el JWT para el usuario con los roles
            String token = generateJWTToken(usuario);

            // Establecer el JWT en el objeto Usuario y actualizar en la base de datos
            usuario.setToken(token);
            usuarioRepository.save(usuario);  // Guardar el usuario con el nuevo JWT en la base de datos

            System.out.println("Token generado y guardado para el usuario: " + token);

            return usuario;
        }

        return null;  // Si el usuario no existe o las credenciales no son válidas
    }

    private String generateJWTToken(Usuario usuario) {
        // Obtener los roles como String (ejemplo: "ROLE_USER,ROLE_ADMIN")
        String roles = usuario.getROLL();

        // Convertir los roles en una lista de autoridades (roles) para el JWT
        List<String> roleList = List.of(roles.split(","));

        // Generar el JWT
        String token = Jwts.builder()
                .setId("JWT")
                .setSubject(usuario.getUsername())  // Nombre de usuario
                .claim("authorities", roleList)     // Asignar los roles al token
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 600000))  // Expiración en 10 minutos
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())  // Firmar el token con la clave secreta
                .compact();

        return "Bearer " + token;  // Retornar el token con el prefijo "Bearer"
    }
}

