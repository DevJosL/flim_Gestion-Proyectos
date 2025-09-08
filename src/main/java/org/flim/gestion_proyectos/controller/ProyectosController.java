package org.flim.gestion_proyectos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_proyectos.entity.Proyecto;
import org.flim.gestion_proyectos.entity.Usuario;
import org.flim.gestion_proyectos.entity.UsuarioProyecto;
import org.flim.gestion_proyectos.service.IProyectoService;
import org.flim.gestion_proyectos.service.IUsuarioProyectoService;
import org.flim.gestion_proyectos.service.IUsuarioService;
import org.flim.gestion_proyectos.service.UsuarioService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Componente generico
@Component
// Alcance de tipo VIEW
@ViewScoped
// Getter y Setter de Lombok
@Data

public class ProyectosController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProyectosController.class);

    @Autowired
    private IProyectoService proyectoService;

    private List<Proyecto> proyectos;
    private Proyecto proyectoSeleccionado;
    private List<UsuarioProyecto> usuarioProyectos;
    private UsuarioProyecto usuarioProyecto;

    @Autowired
    private IUsuarioProyectoService usuarioProyectoService;

    @Autowired
    private UsuarioService usuarioService;

    private Usuario usuario;

    private List<Usuario> usuarios;
    private List<String> usuarioDisponible;

    private Integer usuarioSeleccionadoId;

    @PostConstruct
    public void init() {
        cargarProyectos();
        usuarios = usuarioService.listarUsuario();
        usuarioDisponible = new ArrayList<>();
        for (Usuario usu : usuarios){
            usuarioDisponible.add(usu.getIdUsuario() + " " +usu.getNombreUsuario());
        }
        usuarioProyectos = new ArrayList<>();
    }

    public void cargarProyectos() {
        Usuario usuarioLogueado = (Usuario) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("usuarioLogueado");
        this.proyectos = proyectoService.buscarProyectoPorIdUser(usuarioLogueado.getIdUsuario());
        logger.info("Proyectos cargados: " + proyectos);
    }

    public void agregarProyecto() {
        this.proyectoSeleccionado = new Proyecto();
//        Usuario usuarioLogueado = (Usuario) FacesContext.getCurrentInstance()
//                .getExternalContext().getSessionMap().get("usuarioLogueado");
        this.usuarioProyecto = new UsuarioProyecto();
//        this.usuarioProyecto.setIdProyecto(this.proyectoSeleccionado.getIdProyecto());
//        this.usuarioProyecto.setIdUsuario(usuarioLogueado.getIdUsuario());
        //this.proyectoSeleccionado.setIdUsuario(usuarioLogueado.getIdUsuario());
    }

    public void guardarProyecto() {
        try {
            // Si no detecta ID es un nuevo proyecto
            if (this.proyectoSeleccionado.getIdProyecto() == null) {
                proyectoService.guardarContacto(this.proyectoSeleccionado);
                proyectos.add(this.proyectoSeleccionado);

                Usuario usuarioLogueado = (Usuario) FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap().get("usuarioLogueado");

                usuarioProyecto = new UsuarioProyecto();
                usuarioProyecto.setIdUsuario(usuarioLogueado.getIdUsuario());
                usuarioProyecto.setIdProyecto(this.proyectoSeleccionado.getIdProyecto());

                usuarioProyectoService.guardarUsuarioProyecto(usuarioProyecto);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto creado.", ""));
            } else {
                // Si detecta ID es acutalzacion
                proyectoService.guardarContacto(this.proyectoSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto actualizado.", ""));
            }

            // Oculta la ventana modal
            PrimeFaces.current().executeInitScript("PF('ventanaModalProyecto').hide()");

            PrimeFaces.current().ajax().update("formulario-proyectos:mensaje-emergente",
                    "formulario-proyectos:tabla-proyectos");

            // Actualiza la lista para mostrar cambios
            // cargarProyectos();

            // Nulleamos el proyecto para limpiarlo
            this.proyectoSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al guardar el proyecto: " + e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo guardar el proyecto.", ""));
        }
    }

    public void eliminarProyecto() {
        try {
            proyectoService.eliminarContacto(this.proyectoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proyecto eliminado."));

            // Actualiza la lista para mostrar cambios
            cargarProyectos();

            this.proyectos.remove(this.proyectoSeleccionado);

            // Nulleamos el proyecto para limpiarlo
            this.proyectoSeleccionado = null;
            PrimeFaces.current().ajax().update("formulario-proyectos:mensaje-emergente",
                    "formulario-proyectos:tabla-proyectos");

        } catch (Exception e) {
            logger.error("Error al eliminar el proyecto: " + e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar el proyecto.", ""));
        }
    }

    public void abrirProyecto() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("proyectoIngresado", this.proyectoSeleccionado);

            logger.info(System.lineSeparator() + "Ingresando al proyecto: " + this.proyectoSeleccionado
                    + System.lineSeparator());

            FacesContext.getCurrentInstance().getExternalContext().redirect("/fichaView.xhtml");
        } catch (Exception e) {
            logger.info("No se pudo ingresar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void anadirUsuario(){
        try {
        proyectoService.guardarContacto(this.proyectoSeleccionado);
        UsuarioProyecto usuPry = new UsuarioProyecto();
        usuPry.setIdUsuario(this.usuarioSeleccionadoId);
        usuPry.setIdProyecto(this.proyectoSeleccionado.getIdProyecto());

        usuarioProyectoService.guardarUsuarioProyecto(usuPry);
        usuarioProyectos.add(usuPry);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Usuario añadido al proyecto correctamente.", ""));

        PrimeFaces.current().executeInitScript("PF('ventanaModalAnadir').hide()");
        PrimeFaces.current().ajax().update("formulario-proyectos:tabla-proyectos",
                "formulario-proyectos:mensaje-emergente");

        this.usuarioSeleccionadoId = null;
        } catch (Exception e){
            logger.info("Error al añadir usuario " + e.getMessage());
            e.printStackTrace();
        }
    }
}   