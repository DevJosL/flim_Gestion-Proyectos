package org.flim.gestion_proyectos.repository;

import org.flim.gestion_proyectos.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    //Remplazo del DAO

    //JPA SPRING lo traduce como select p from Proyectos p where p.idUsuario = ?
    List<Proyecto> findByIdUsuario(Integer idUsuario);
}
