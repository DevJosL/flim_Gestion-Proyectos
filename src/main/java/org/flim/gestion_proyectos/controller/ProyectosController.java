package org.flim.gestion_proyectos.controller;

/*
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_proyectos.entity.Proyecto;
import org.flim.gestion_proyectos.service.IProyectoService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

//Componente generico
@Component
//Alcance de tipo VIEW
@ViewScoped
//Getter y Setter de Lombok
@Data
*/
public class ProyectosController {
/*
public class ProyectosController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProyectosController.class);

    @Autowired
    private IProyectoService proyectoService;

    private List<Proyecto> proyectos;
    private Proyecto proyectoSeleccionado;

    @PostConstruct
    public void init() {
        cargarProyectos();
    }

    public void cargarProyectos() {
        this.proyectos = proyectoService.listarProyectos();
        logger.info("Proyectos cargados: " + proyectos);
    }

    public void agregarProyecto() {
        this.proyectoSeleccionado = new Proyecto();
    }

    public void guardarProyecto() {
        try {
            // Si no detecta ID es un nuevo proyecto
            if (this.proyectoSeleccionado.getIdProyecto() == null) {
                proyectoService.guardarContacto(this.proyectoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto creado."));
            } else {
                // Si detecta ID es acutalzacion
                proyectoService.guardarContacto(this.proyectoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto actualizado."));
            }

            // Oculta la ventana modal
            PrimeFaces.current().executeScript("PF('dialogoProyecto').hide()");

            // Actualiza la lista para mostrar cambios
            cargarProyectos();

            // Nulleamos el proyecto para limpiarlo
            this.proyectoSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al guardar el proyecto: " + e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo guardar el proyecto."));
        }
    }

    public void eliminarProyecto() {
        try {
            proyectoService.eliminarContacto(this.proyectoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto eliminado."));

            // Actualiza la lista para mostrar cambios
            cargarProyectos();

            // Nulleamos el proyecto para limpiarlo
            this.proyectoSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al eliminar el proyecto: " + e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar el proyecto."));
        }
    }
     */
}