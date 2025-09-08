package org.flim.gestion_proyectos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_proyectos.entity.Ficha;
import org.flim.gestion_proyectos.entity.Tarea;
import org.flim.gestion_proyectos.service.IFichaService;
import org.flim.gestion_proyectos.service.ITareaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("tareaController")
@ViewScoped
@Data
public class TareaController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(TareaController.class);

    @Autowired
    private ITareaService tareaService;

    private List<Tarea> tareas;
    private Tarea tareaSeleccionada;
    private Ficha fichaIngresada;

    @Autowired
    private IFichaService fichaService;

    private List<Ficha> fichasProyecto;

    @PostConstruct
    public void init() {
        this.fichaIngresada = (Ficha) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("fichaIngresada");
        if (this.fichaIngresada != null) {
            cargarTareas();
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("fichasView.xhtml");
            } catch (Exception e) {
                logger.error("Error al redireccionar: " + e.getMessage(), e);
            }
        }
    }

    public void cargarTareas() {
        this.tareas = tareaService.buscarTareaporIdFicha(this.fichaIngresada.getIdFicha());
        logger.info("Tareas cargadas para la ficha " + this.fichaIngresada.getNombreFicha() + ": " + tareas);
    }

    public void agregarTarea() {
        this.tareaSeleccionada = new Tarea();
        if (this.fichaIngresada != null) {
        this.tareaSeleccionada.setFicha(this.fichaIngresada);
        }
    }

    public void guardarTarea() {
        try {
            if (this.tareaSeleccionada.getIdTarea() == null) {
                tareaService.guardarTarea(this.tareaSeleccionada);
                tareas.add(this.tareaSeleccionada);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarea creada.", ""));
            } else {
                tareaService.guardarTarea(this.tareaSeleccionada);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarea actualizada.", ""));
            }

            PrimeFaces.current().executeInitScript("PF('ventanaModalTarea').hide()");
            PrimeFaces.current().ajax().update("formulario-tareas:mensaje-emergente",
                    "formulario-tareas:tabla-tareas");

            this.tareaSeleccionada = null;
        } catch (Exception e) {
            logger.error("Error al guardar la tarea: " + e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo guardar la tarea.", ""));
        }
    }

    public void eliminarTarea() {
        try {
            tareaService.eliminarTarea(this.tareaSeleccionada);
            tareas.remove(this.tareaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarea eliminada."));

            PrimeFaces.current().ajax().update("formulario-tareas:mensaje-emergente",
                    "formulario-tareas:tabla-tareas");

            this.tareaSeleccionada = null;
        } catch (Exception e) {
            logger.error("Error al eliminar la tarea: " + e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar la tarea.", ""));
        }
    }

    public void regresarAFichas() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("fichaIngresada");
            FacesContext.getCurrentInstance().getExternalContext().redirect("fichaView.xhtml");
        } catch (Exception e) {
            logger.error("Error al regresar a la vista de fichas: " + e.getMessage(), e);
        }
    }
}