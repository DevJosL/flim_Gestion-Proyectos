package org.flim.gestion_proyectos.repository;

import org.flim.gestion_proyectos.entity.Proyecto;
import org.flim.gestion_proyectos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    //Remplazo del DAO

    @Query("select p from Proyectos p inner join  UsuariosProyectos up  on up.idProyecto = p.idProyecto inner join Usuarios u on u.idUsuario = up.idUsuario where u.idUsuario = ?1")
    List<Proyecto> findByIdUsuario(Integer idUsuario);
}
