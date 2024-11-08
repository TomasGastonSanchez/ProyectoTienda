package com.serendipia.proyectoTienda.Repository;

import Entidades.Cliente;
import Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {//capaz cambiar log por integer

    Usuario findByUsername(String username);  // Esto buscará un usuario por su username

    List<Usuario> findByROLL(String ROLL);
}
