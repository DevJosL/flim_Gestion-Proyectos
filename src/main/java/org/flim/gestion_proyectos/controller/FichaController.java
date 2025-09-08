package org.flim.gestion_proyectos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_proyectos.entity.Ficha;
import org.flim.gestion_proyectos.entity.Proyecto;
import org.flim.gestion_proyectos.service.IFichaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("fichaController")
@ViewScoped
@Data
public class FichaController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(FichaController.class);

    @Autowired
    private IFichaService fichaService;

    private List<Ficha> fichas;
    private Ficha fichaSeleccionada;
    private Proyecto proyectoIngresado;

    @PostConstruct
    public void init() {
        cargarFichas();
        /*this.proyectoIngresado = (Proyecto) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("proyectoIngresado");
        if (this.proyectoIngresado != null) {
            cargarFichas();
        } else {
            // Redirige si no hay un proyecto en la sesión
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("proyectosView.xhtml");
            } catch (Exception e) {
                logger.error("Error al redireccionar: " + e.getMessage(), e);
            }
        }*/
    }

    public void cargarFichas() {
        this.proyectoIngresado = (Proyecto) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("proyectoIngresado");
        this.fichas = fichaService.buscarFichaPorIdProyecto(proyectoIngresado.getIdProyecto());
        logger.info("Fichas cargadas para el proyecto " + this.proyectoIngresado.getNombre() + ": " + fichas);
    }

    public void agregarFicha() {
        this.fichaSeleccionada = new Ficha();
        this.fichaSeleccionada.setIdProyecto(proyectoIngresado.getIdProyecto());
    }

    public void guardarFicha() {
        try {
            if (this.fichaSeleccionada.getIdFicha() == null) {
                fichaService.guardarFicha(this.fichaSeleccionada);
                fichas.add(this.fichaSeleccionada);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Ficha creada.", ""));
            } else {
                fichaService.guardarFicha(this.fichaSeleccionada);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Ficha actualizada.", ""));
            }

            PrimeFaces.current().executeInitScript("PF('ventanaModalFicha').hide()");
            PrimeFaces.current().ajax().update("formulario-fichas:mensaje-emergente",
                    "formulario-fichas:tabla-fichas");

            this.fichaSeleccionada = null;
        } catch (Exception e) {
            logger.error("Error al guardar la ficha: " + e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo guardar la ficha.", ""));
        }
    }

    public void eliminarFicha() {
        try {
            fichaService.eliminarFicha(this.fichaSeleccionada);
            fichas.remove(this.fichaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ficha eliminada."));

            PrimeFaces.current().ajax().update("formulario-fichas:mensaje-emergente",
                    "formulario-fichas:tabla-fichas");

            this.fichaSeleccionada = null;
        } catch (Exception e) {
            logger.error("Error al eliminar la ficha: " + e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar la ficha.", ""));
        }
    }

    public void regresarAProyectos() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("proyectoIngresado");
            FacesContext.getCurrentInstance().getExternalContext().redirect("proyectoView.xhtml");
        } catch (Exception e) {
            logger.error("Error al regresar a la vista de proyectos: " + e.getMessage(), e);
        }
    }
    public void abrirFicha() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("fichaIngresada", this.fichaSeleccionada);

            logger.info(System.lineSeparator() + "Ingresando a Ficha: " + this.fichaSeleccionada.getIdFicha()
                    + System.lineSeparator());

            FacesContext.getCurrentInstance().getExternalContext().redirect("tareaView.xhtml");
        } catch (Exception e) {
            logger.info("No se pudo ingresar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}