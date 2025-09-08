package org.flim.gestion_proyectos.repository;

import org.flim.gestion_proyectos.entity.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFichaRepository extends JpaRepository<Ficha,Integer> {

    //@Query("select f from Fichas f where f.idProyecto = ?1")
    List<Ficha> findByIdProyecto(Integer proyectoId);
}

