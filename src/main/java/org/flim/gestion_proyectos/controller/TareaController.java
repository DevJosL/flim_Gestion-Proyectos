package org.flim.gestion_proyectos.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import jakarta.faces.view.ViewScoped;
import org.flim.gestion_proyectos.entity.Tarea;
import org.flim.gestion_proyectos.service.ITareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ViewScoped
@Data
public class TareaController {

    @Autowired
    ITareaService tareaService;
    private List<Tarea> tareas;
    private Tarea tareaSeleccionada;
    private static Logger logger = LoggerFactory.getLogger(TareaController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.tareas = this.tareaService.listarTareas();
        this.tareas.forEach(tarea -> logger.info(tarea.toString()));
    }

    public void agregarTarea(){
        this.tareaSeleccionada = new Tarea();
    }

    public void guardarTarea(){
        logger.info("Tarea a guardar" + this.tareaSeleccionada);

        if (this.tareaSeleccionada.getIdTarea()==null){
            this.tareaService.guardarTarea(this.tareaSeleccionada);
            this.tareas.add(this.tareaSeleccionada);
        }else{
            this.tareaService.guardarTarea(this.tareaSeleccionada);
        }
        this.tareaSeleccionada = null;
    }
    public void eliminarTarea(){
        logger.info("Tarea a eliminar: " + this.tareaSeleccionada);
        this.tareaService.eliminarTarea(this.tareaSeleccionada);
        this.tareas.remove(this.tareaSeleccionada);
        this.tareaSeleccionada = null;
    }
}
