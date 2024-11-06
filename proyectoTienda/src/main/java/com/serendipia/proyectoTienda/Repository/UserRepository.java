package com.serendipia.proyectoTienda.Repository;

import Entidades.Cliente;
import Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);  // Esto buscará un usuario por su username

    // Si necesitas encontrar usuarios por rol, puedes hacerlo con una consulta personalizada
    List<Usuario> findByROLL(String ROLL);  // Esto busca usuarios por el nombre del rol
}
