package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Tarea;

import java.util.List;

public interface ITareaService {
    public List<Tarea> listarTareas();
    public Tarea buscarTareaporId(Integer id);
    public List<Tarea> buscarTareaporIdFicha(Integer idFicha);
    public void guardarTarea(Tarea tarea);
    public void eliminarTarea(Tarea tarea);
}
