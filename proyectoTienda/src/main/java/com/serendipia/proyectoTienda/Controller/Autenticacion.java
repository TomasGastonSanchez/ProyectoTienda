package com.serendipia.proyectoTienda.Controller;

import Entidades.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestBody;


import io.jsonwebtoken.impl.crypto.MacProvider;

@RestController
public class Autenticacion {

    // Cambia el método login así:
    @PostMapping("/api/user")
    public Usuario login(@RequestBody UsuarioLoginRequest request) {
        String token = getJWTToken(request.getUsername());
        Usuario user = new Usuario();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setToken(token);
        return user;
    }

    // Clase para recibir la solicitud
    public static class UsuarioLoginRequest {
        private String username;
        private String password;

        // Getters y setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }



    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }


}
