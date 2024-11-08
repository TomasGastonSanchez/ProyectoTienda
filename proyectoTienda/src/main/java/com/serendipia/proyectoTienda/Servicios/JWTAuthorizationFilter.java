package com.serendipia.proyectoTienda.Servicios;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import  com.serendipia.proyectoTienda.Controller.*;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX_JWT = "Bearer ";
    private final String PREFIX_TOKEN = "Token "; // Token simple
    private final String SECRET = JWTConfig.SECRET_KEY; // Asegúrate de usar un enfoque más seguro para las claves

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(HEADER);
        String requestURI = request.getRequestURI();

        // Permitir acceso sin token a la ruta /api/user
        if (requestURI.equals("/api/user") || requestURI.equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Si la solicitud usa JWT (Bearer Token), procedemos con la autenticación JWT
        if (authHeader != null && authHeader.startsWith(PREFIX_JWT)) {
            String jwtToken = authHeader.replace(PREFIX_JWT, "");
            try {
                Claims claims = validateJWTToken(jwtToken);
                if (claims != null && claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Estado 401
                    return;
                }
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
                return;
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
                return;
            }
        }
        // Si el header contiene Token (Token simple), procedemos con la autenticación simple
        else if (authHeader != null && authHeader.startsWith(PREFIX_TOKEN)) {
            String simpleToken = authHeader.replace(PREFIX_TOKEN, "");
            if (validateSimpleToken(simpleToken)) {
                setUpSimpleAuthentication(simpleToken);
            } else {
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Estado 401
                return;
            }
        }
        // Si no contiene ningún token, limpiamos el contexto de seguridad
        else {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Estado 401
            return;
        }

        filterChain.doFilter(request, response);
    }

    // Método para validar el JWT
    private Claims validateJWTToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para validar el token simple
    private boolean validateSimpleToken(String token) {
        String validToken = "hola"; // Aquí deberías validar el token según sea necesario
        return validToken.equals(token);
    }

    // Método para configurar la autenticación con JWT
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    // Método para configurar la autenticación con el token simple
    private void setUpSimpleAuthentication(String token) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER"); // Ajusta según sea necesario
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token, null, List.of(authority));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
