package org.flim.gestion_proyectos.repository;

import org.flim.gestion_proyectos.entity.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFichaRepository extends JpaRepository<Ficha,Integer> {

    List<Ficha> findByNombreFicha(String nombreFicha);
}

