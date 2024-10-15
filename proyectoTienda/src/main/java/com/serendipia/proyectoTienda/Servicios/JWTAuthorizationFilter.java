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

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX_JWT = "Bearer ";
    private final String PREFIX_TOKEN = "Token "; // Token simple
    private final String SECRET = "mySecretKey"; // Asegúrate de usar un enfoque más seguro para las claves

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(HEADER);
        String requestURI = request.getRequestURI();

        // Permitir acceso sin token a la ruta /api/user
        if (requestURI.equals("/api/user")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (requestURI.startsWith("/api/token-requerido")) {
                // Manejo de token simple
                if (authHeader != null && authHeader.startsWith(PREFIX_TOKEN)) {
                    String simpleToken = authHeader.replace(PREFIX_TOKEN, "");
                    if (validateSimpleToken(simpleToken)) {
                        setUpSimpleAuthentication(simpleToken);
                    } else {
                        SecurityContextHolder.clearContext();
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Estado 401
                        return;
                    }
                } else {
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Estado 401
                    return;
                }
            } else if (authHeader != null && authHeader.startsWith(PREFIX_JWT)) {
                // Manejo de JWT
                String jwtToken = authHeader.replace(PREFIX_JWT, "");
                Claims claims = validateJWTToken(jwtToken);
                if (claims != null && claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Estado 401
                    return;
                }
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

        filterChain.doFilter(request, response);
    }


    private Claims validateJWTToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean validateSimpleToken(String token) {
        // Definir el token válido
        String validToken = "hola"; // Este valor puede ser una constante o configurado externamente

        // Comprobar si el token recibido es igual al token válido
        return validToken.equals(token);
    }

    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void setUpSimpleAuthentication(String token) {
        // Aquí puedes establecer la autenticación para el token simple
        // Suponiendo que el token simple tiene un solo rol
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER"); // Ajusta según sea necesario
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token, null, List.of(authority));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}

