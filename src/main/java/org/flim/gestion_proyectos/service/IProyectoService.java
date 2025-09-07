package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Proyecto;
import org.flim.gestion_proyectos.entity.Usuario;

import java.util.List;

public interface IProyectoService {
    public List<Proyecto> listarProyectos();
    public List<Proyecto> buscarProyectoPorIdUser(Integer idUser);
    public Proyecto buscarProyectoPorId(Integer id);
    public void guardarContacto(Proyecto proyecto);
    public void eliminarContacto(Proyecto proyecto);
}
