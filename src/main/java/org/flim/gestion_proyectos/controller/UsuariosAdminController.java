package org.flim.gestion_proyectos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_proyectos.entity.Usuario;
import org.flim.gestion_proyectos.service.IUsuarioService;
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

public class UsuariosAdminController implements Serializable {

    // Inyeccion de dependencia
    @Autowired
    private IUsuarioService usuarioService;

    private List<Usuario> usuarios;
    private Usuario usuarioSeleccionado;

    // Es una solicitud despues de haber iniciado el componente para cargar los datos iniciales o listarlos
    @PostConstruct
    public void init() {
        cargarUsuarios();
    }

    // Metodo de listar usarios
    private void cargarUsuarios() {
        this.usuarios = usuarioService.listarUsuario();
    }

    // Metodo de formulario para un nuevo usuario
    public void nuevoUsuario() {
        this.usuarioSeleccionado = new Usuario();
    }

    // Metodo para guardar un nuevo usuario
    public void guardarUsuario() {
        // Nos faciliamos procesos mandando a llamar a los servicios
        usuarioService.guardarUsuario(this.usuarioSeleccionado);

        // cada que se agregue un nuevo usuario, vuelve a mostrar ya actualizada la lista
        cargarUsuarios();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario guardado correctamente."));
    }

    // Metodo que elimina un usuario (realmente no seria lo ideal pero es tocar db y agregar estado de activo o inactivo)
    public void eliminarUsuario() {
        usuarioService.eliminarUsuario(this.usuarioSeleccionado);

        // Vuelve a cargar la lista
        cargarUsuarios();

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario eliminado correctamente."));
    }
}
