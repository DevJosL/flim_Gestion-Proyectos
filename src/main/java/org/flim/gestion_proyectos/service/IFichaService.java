package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Ficha;

import java.util.List;

public interface IFichaService {
    public List<Ficha> listarFichas();
    public Ficha buscarFichaPorId(Integer id);
    public Ficha buscarFichaPorNombre(String nombreFicha);
    public void guardarFicha(Ficha ficha);
    public void eliminarFicha(Ficha ficha);
}
