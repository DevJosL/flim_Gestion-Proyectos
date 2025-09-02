package org.flim.gestion_proyectos.repository;

import org.flim.gestion_proyectos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
