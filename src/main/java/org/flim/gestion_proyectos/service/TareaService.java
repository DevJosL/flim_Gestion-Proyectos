package org.flim.gestion_proyectos.service;

import org.flim.gestion_proyectos.entity.Tarea;
import org.flim.gestion_proyectos.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService implements ITareaService{
    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public List<Tarea> listarTareas() {
        List<Tarea> tareas = tareaRepository.findAll();
        return tareas;
    }

    @Override
    public Tarea buscarTareaporId(Integer id) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        return tarea;
    }

    @Override
    public List<Tarea> buscarTareaporIdFicha(Integer idFicha) {
        List<Tarea> tareas = tareaRepository.findByFichaIdFicha(idFicha);
        return tareas;
    }

    @Override
    public void guardarTarea(Tarea tarea) {
        tareaRepository.save(tarea);

    }

    @Override
    public void eliminarTarea(Tarea tarea) {
        tareaRepository.delete(tarea);
    }
}
